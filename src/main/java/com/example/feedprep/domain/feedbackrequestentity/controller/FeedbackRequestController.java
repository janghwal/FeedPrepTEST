package com.example.feedprep.domain.feedbackrequestentity.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.service.FeedbackRequestService;

@RestController
@RequestMapping("requests/")
@RequiredArgsConstructor
public class FeedbackRequestController {
    private final FeedbackRequestService feedbackRequestService;

	@PostMapping
	public ResponseEntity<FeedbackRequestEntityResponseDto> createRequest(
		@AuthUser Long userId,
		@RequestBody FeedbackRequestDto dto){

		return new ResponseEntity<>(feedbackRequestService.createRequest(userId, dto), HttpStatus.CREATED);

	}
}
