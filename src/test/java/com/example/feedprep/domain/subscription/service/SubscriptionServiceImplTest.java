package com.example.feedprep.domain.subscription.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.subscription.entity.Subscription;
import com.example.feedprep.domain.subscription.repository.SubscriptionRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {

	@Mock
	private SubscriptionRepository subscriptionRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private SubscriptionServiceImpl subscriptionService;

	@Test
	void subscribe_성공() {
		Long senderId = 1L;
		Long receiverId = 2L;
		User sender = mock(User.class);
		User receiver = mock(User.class);

		when(userRepository.findById(senderId)).thenReturn(Optional.of(sender));
		when(userRepository.findById(receiverId)).thenReturn(Optional.of(receiver));

		assertDoesNotThrow(() -> subscriptionService.subscribe(senderId, receiverId));
		verify(subscriptionRepository, times(1)).save(any());
	}

	@Test
	void subscribe_실패_자신이_자신을_구독_요청() {
		Long senderId = 1L;
		Long receiverId = 1L;

		CustomException exception = assertThrows(CustomException.class, () -> {
			subscriptionService.subscribe(senderId, receiverId);
		});

		assertEquals(ErrorCode.CANNOT_SUBSCRIBE_SELF, exception.getErrorCode());
	}

	@Test
	void subscribe_실패_sender가_존재하지_않음() {
		Long senderId = 1L;
		Long receiverId = 2L;

		when(userRepository.findById(senderId)).thenReturn(Optional.empty());

		CustomException exception = assertThrows(CustomException.class, () -> {
			subscriptionService.subscribe(senderId, receiverId);
		});

		assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
	}

	@Test
	void unsubscribe_성공() {
		Long senderId = 1L;
		Long subscriptionId = 1L;

		User sender = mock(User.class);
		Subscription subscription = mock(Subscription.class);

		when(subscriptionRepository.findByIdOrElseThrow(subscriptionId)).thenReturn(subscription);
		when(subscription.getSender()).thenReturn(sender);
		when(sender.getUserId()).thenReturn(senderId);

		assertDoesNotThrow(() -> subscriptionService.unsubscribe(senderId, subscriptionId));
		verify(subscriptionRepository, times(1)).delete(any());
	}

	@Test
	void unsubscribe_실패_구독_정보가_없음() {
		Long senderId = 1L;
		Long subscriptionId = 1L;

		CustomException customException = new CustomException(ErrorCode.SUBSCRIPTION_NOT_FOUND);

		when(subscriptionRepository.findByIdOrElseThrow(subscriptionId)).thenThrow(customException);

		CustomException exception = assertThrows(CustomException.class, () -> {
			subscriptionService.unsubscribe(senderId, subscriptionId);
		});

		assertEquals(ErrorCode.SUBSCRIPTION_NOT_FOUND, exception.getErrorCode());
	}

	@Test
	void unsubscribe_실패_구독의_주인이_아님() {
		Long senderId = 1L;
		Long anotherId = 2L;
		Long subscriptionId = 1L;

		User sender = mock(User.class);
		Subscription subscription = mock(Subscription.class);

		when(subscriptionRepository.findByIdOrElseThrow(subscriptionId)).thenReturn(subscription);
		when(subscription.getSender()).thenReturn(sender);
		when(sender.getUserId()).thenReturn(anotherId);

		CustomException exception = assertThrows(CustomException.class, () -> {
			subscriptionService.unsubscribe(senderId, subscriptionId);
		});

		assertEquals(ErrorCode.UNAUTHORIZED_SUBSCRIPTION_ACCESS, exception.getErrorCode());
	}
}
