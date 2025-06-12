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
import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
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
	public ResponseEntity<ApiResponseDto< FeedbackResponseDto>> createFeedback(
		@AuthUser Long tutorId,
		@RequestParam Long requestId,
		@Validated @RequestBody FeedbackWriteRequestDto dto
	){

		return ResponseEntity.status(HttpStatus.CREATED)
			   .body(ApiResponseDto.success(SuccessCode.OK_SUCCESS_FEEDBACK_CREATED,
			    feedbackService.createFeedback(tutorId, requestId,dto)));
	}


	@PatchMapping("/feedback/{feedbackId}")
	public ResponseEntity<ApiResponseDto<FeedbackResponseDto>> updateFeedback (
		@AuthUser Long tutorId,
		@PathVariable Long feedbackId,
		@Validated @RequestBody FeedbackWriteRequestDto dto
	){
		return  ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponseDto.success(SuccessCode.OK_SUCCESS_FEEDBACK_UPDATE,
				feedbackService.updateFeedback(tutorId, feedbackId, dto)));
	}
}
