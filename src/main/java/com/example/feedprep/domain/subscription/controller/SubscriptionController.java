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
		// @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
		@RequestParam Long userId
	) {
		// 임시 senderId
		Long senderId = 1L;

		subscriptionService.subscribe(senderId, userId);

		return ResponseEntity.status(SuccessCode.SUBSCRIBED.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.SUBSCRIBED));
	}

	@DeleteMapping("/{subscriptionId}")
	public ResponseEntity<ApiResponseDto<Void>> unsubscribe(
		// @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
		@PathVariable Long subscriptionId) {
		// 임시 senderId
		Long senderId = 1L;

		subscriptionService.unsubscribe(senderId, subscriptionId);

		return ResponseEntity.status(SuccessCode.UNSUBSCRIBED.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.UNSUBSCRIBED));
	}

	@GetMapping
	public ResponseEntity<ApiResponseDto<List<SubscriptionResponseDto>>> getSubscriptionsInfo(
		// @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal,
		@RequestParam GetType getType
	) {
		// 임시 requesterId
		Long requesterId  = 1L;

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
