package com.example.feedprep.domain.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.subscription.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	default Subscription findByIdOrElseThrow(Long subscriptionId) {
		return findById(subscriptionId)
			.orElseThrow(() -> new CustomException(ErrorCode.SUBSCRIPTION_NOT_FOUND));
	}
}
