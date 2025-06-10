package com.example.feedprep.domain.feedback.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.domain.feedback.dto.request.FeedbackWriteRequestDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackResponseDto;
import com.example.feedprep.domain.feedback.service.FeedbackService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FeedbackController {
	private final FeedbackService feedbackService;

	@PostMapping("/feedback")
	public ResponseEntity<FeedbackResponseDto> createFeedback(
		@AuthUser Long tutorId,
		@RequestParam Long requestId,
		@Validated @RequestBody FeedbackWriteRequestDto dto
	){

		return new ResponseEntity<>(feedbackService.createFeedback(tutorId, requestId,dto),HttpStatus.CREATED);
	}

	@PatchMapping("/feedback/{feedbackId}")
	public ResponseEntity<FeedbackResponseDto> updateFeedback (
		@AuthUser Long tutorId,
		@PathVariable Long feedbackId,
		@Validated @RequestBody FeedbackWriteRequestDto dto
	){
		return new ResponseEntity<>(feedbackService.updateFeedback(tutorId, feedbackId, dto), HttpStatus.OK);
	}
}
