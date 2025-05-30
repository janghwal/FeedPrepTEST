package com.example.feedprep.domain.subscription.entity;

import com.example.feedprep.common.entity.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	// @ManyToOne
	// private Users sender;
	// @ManyToOne
	// private Users receiver;

}
