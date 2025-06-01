package com.example.feedprep.domain.subscription.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
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
}
