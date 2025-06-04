package com.example.feedprep.domain.subscription.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.domain.subscription.dto.SubscriptionResponseDto;
import com.example.feedprep.domain.subscription.enums.GetType;
import com.example.feedprep.domain.subscription.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
	private final SubscriptionService subscriptionService;

	@PostMapping
	public ResponseEntity<ApiResponseDto<Void>> subscribe(
		@AuthUser Long senderId,
		@RequestParam Long userId
	) {
		subscriptionService.subscribe(senderId, userId);

		return ResponseEntity.status(SuccessCode.SUBSCRIBED.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.SUBSCRIBED));
	}

	@DeleteMapping("/{subscriptionId}")
	public ResponseEntity<ApiResponseDto<Void>> unsubscribe(
		@AuthUser Long senderId,
		@PathVariable Long subscriptionId) {

		subscriptionService.unsubscribe(senderId, subscriptionId);

		return ResponseEntity.status(SuccessCode.UNSUBSCRIBED.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.UNSUBSCRIBED));
	}

	@GetMapping
	public ResponseEntity<ApiResponseDto<List<SubscriptionResponseDto>>> getSubscriptionsInfo(
		@AuthUser Long requesterId,
		@RequestParam GetType getType
	) {
		List<SubscriptionResponseDto> subscriptionList;
		switch (getType) {
			case SUBSCRIBERS -> {
				subscriptionList = subscriptionService.getSubscribers(requesterId);
				return ResponseEntity.status(SuccessCode.SUBSCRIBER_LIST.getHttpStatus())
					.body(ApiResponseDto.success(SuccessCode.SUBSCRIBER_LIST, subscriptionList));
			}
			case SUBSCRIPTIONS -> {
				subscriptionList = subscriptionService.getSubscriptions(requesterId);
				return ResponseEntity.status(SuccessCode.SUBSCRIPTION_LIST.getHttpStatus())
					.body(ApiResponseDto.success(SuccessCode.SUBSCRIPTION_LIST, subscriptionList));
			}
		}
		return null;
	}
}
