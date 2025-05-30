package com.example.feedprep.domain.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.domain.subscription.entity.Subscriptions;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {
}
