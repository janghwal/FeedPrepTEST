package com.example.feedprep.domain.subscription.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.subscription.dto.SubscriptionResponseDto;
import com.example.feedprep.domain.subscription.entity.Subscription;
import com.example.feedprep.domain.subscription.repository.SubscriptionRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import com.example.feedprep.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{

	private final UserRepository userRepository;
	private final SubscriptionRepository subscriptionRepository;

	@Override
	public void subscribe(Long senderId, Long userId) {
		// 자기 자신 구독 요청
		if(Objects.equals(senderId, userId)){
			throw new CustomException(ErrorCode.CANNOT_SUBSCRIBE_SELF);
		}

		User sender = userRepository.findByIdOrElseThrow(senderId);
		User receiver = userRepository.findByIdOrElseThrow(userId);

		// receiver가 승인된 튜터가 아닐 경우
		if(receiver.getRole() != UserRole.APPROVED_TUTOR || sender.getRole() != UserRole.STUDENT){
			throw new CustomException(ErrorCode.BAD_REQUEST);
		}

		// 중복 구독
		if(subscriptionRepository.existsByReceiverAndSender(receiver, sender)){
			throw new CustomException(ErrorCode.DUPLICATE_SUBSCRIPTION);
		}

		// 구독은 튜터에게만 가능한 것인가? 의사 결정 후 추가 필요.
		Subscription subscription = new Subscription(sender, receiver);
		subscriptionRepository.save(subscription);
	}

	@Transactional
	@Override
	public void unsubscribe(Long senderId, Long subscriptionId) {
		Subscription subscription = subscriptionRepository.findByIdOrElseThrow(subscriptionId);
		if(!subscription.getSender().getUserId().equals(senderId)) {
			throw new CustomException(ErrorCode.UNAUTHORIZED_SUBSCRIPTION_ACCESS);
		}
		subscriptionRepository.delete(subscription);
	}

	@Override
	public List<SubscriptionResponseDto> getSubscribers(Long requesterId) {
		User requester = userRepository.findByIdOrElseThrow(requesterId);
		List<Subscription> getSubscribers = subscriptionRepository.findByReceiver(requester);

		return getSubscribers.stream()
			.map(subscription -> new SubscriptionResponseDto(subscription.getSender()))
			.toList();
	}

	@Override
	public List<SubscriptionResponseDto> getSubscriptions(Long requesterId) {
		User requester = userRepository.findByIdOrElseThrow(requesterId);
		List<Subscription> getSubscriptions = subscriptionRepository.findBySender(requester);

		return getSubscriptions.stream()
			.map(subscription -> new SubscriptionResponseDto(subscription.getReceiver()))
			.toList();
	}
}
