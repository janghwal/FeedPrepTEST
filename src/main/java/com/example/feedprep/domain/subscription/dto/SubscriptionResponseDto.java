package com.example.feedprep.domain.subscription.dto;

import com.example.feedprep.domain.user.entity.User;

import lombok.Getter;

@Getter
public class SubscriptionResponseDto {
	private Long userId;
	private String name;
	private String email;

	public SubscriptionResponseDto(User user) {
		this.userId = user.getUserId();
		this.name = user.getName();
		this.email = user.getEmail();
	}
}
