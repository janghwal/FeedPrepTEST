package com.example.feedprep.domain.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.domain.subscription.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
