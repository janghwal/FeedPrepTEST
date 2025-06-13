package com.example.feedprep.domain.subscription.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.subscription.entity.Subscription;
import com.example.feedprep.domain.user.entity.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	default Subscription findByIdOrElseThrow(Long subscriptionId) {
		return findById(subscriptionId)
			.orElseThrow(() -> new CustomException(ErrorCode.SUBSCRIPTION_NOT_FOUND));
	}

	List<Subscription> findBySender(User user);
	List<Subscription> findByReceiver(User user);

	boolean existsByReceiverAndSender(User receiver, User sender);
}
