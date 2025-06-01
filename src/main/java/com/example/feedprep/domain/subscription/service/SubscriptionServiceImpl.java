package com.example.feedprep.domain.subscription.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.subscription.entity.Subscription;
import com.example.feedprep.domain.subscription.repository.SubscriptionRepository;
import com.example.feedprep.domain.user.entity.User;
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

		// UserRepository findByIdOrElseThrow 정의되면 수정
		Optional<User> sender = userRepository.findById(senderId);
		if(sender.isEmpty()){
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
		Optional<User> receiver = userRepository.findById(userId);
		if(receiver.isEmpty()){
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}

		// 구독은 튜터에게만 가능한 것인가? 의사 결정 후 추가 필요.

		Subscription subscription = new Subscription(sender.get(), receiver.get());
		subscriptionRepository.save(subscription);
	}
}
