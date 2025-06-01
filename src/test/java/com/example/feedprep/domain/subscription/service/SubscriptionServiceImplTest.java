package com.example.feedprep.domain.subscription.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
