package com.example.feedprep.domain.subscription.service;

public interface SubscriptionService {
	void subscribe(Long senderId, Long userId);

	void unsubscribe(Long senderId, Long subscriptionId);
}
