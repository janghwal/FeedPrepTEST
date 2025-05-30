package com.example.feedprep.domain.subscription.entity;

import com.example.feedprep.common.entity.BaseTimeEntity;
import com.example.feedprep.domain.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "Subscriptions")
@Getter
public class Subscriptions extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriptionId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User sender;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User receiver;

}
