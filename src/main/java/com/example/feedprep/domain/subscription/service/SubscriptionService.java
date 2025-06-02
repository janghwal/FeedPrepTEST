package com.example.feedprep.domain.subscription.service;

import java.util.List;

import com.example.feedprep.domain.subscription.dto.SubscriptionResponseDto;

public interface SubscriptionService {
	void subscribe(Long senderId, Long userId);

	void unsubscribe(Long senderId, Long subscriptionId);

	List<SubscriptionResponseDto> getSubscribers(Long requesterId);

	List<SubscriptionResponseDto> getSubscriptions(Long requesterId);
}
