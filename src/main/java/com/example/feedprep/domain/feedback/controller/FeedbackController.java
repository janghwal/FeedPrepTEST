package com.example.feedprep.domain.feedback.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.domain.feedback.dto.request.FeedbackWriteRequestDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRejectResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestListResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackResponseDto;
import com.example.feedprep.domain.feedback.service.FeedbackService;

@RestController
@RequiredArgsConstructor
public class FeedbackController {
	private final FeedbackService feedbackService;

	@GetMapping("requests/{requestId}")
	public ResponseEntity<FeedbackRequestResponseDto>  getFeedbackRequest(
		@AuthUser Long tutorId,
		@PathVariable  Long requestId
	){
		return  new ResponseEntity<>(feedbackService.getFeedbackRequest(tutorId, requestId), HttpStatus.OK);
	}
	@GetMapping("requests/")
	public ResponseEntity<FeedbackRequestListResponseDto> getFeedbackRequests(
		@AuthUser Long tutorId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size
	){
		return new ResponseEntity<>(feedbackService.getFeedbackRequests(tutorId, page, size), HttpStatus.OK);
	}

	@PostMapping("requests/{requestId}/feedback")
	public ResponseEntity<FeedbackResponseDto> createFeedback(
		@AuthUser Long tutorId,
		@PathVariable Long requestId,
		@RequestBody FeedbackWriteRequestDto dto
	){

		return new ResponseEntity<>(feedbackService.createFeedback(tutorId, requestId,dto),HttpStatus.CREATED);
	}

	@PatchMapping("feedbacks/{feedbackId}")
	public ResponseEntity<FeedbackResponseDto> updateFeedback (
		@AuthUser Long tutorId,
		@PathVariable Long feedbackId,
		@RequestBody FeedbackWriteRequestDto dto
	){
		return new ResponseEntity<>(feedbackService.updateFeedback(tutorId, feedbackId, dto), HttpStatus.OK);
	}

	@DeleteMapping ("feedbacks/{feedbackId}")
	public ResponseEntity<FeedbackRejectResponseDto> rejectFeedback(
		@AuthUser Long tutorId,
		@PathVariable Long feedbackId,
		@RequestBody FeedbackWriteRequestDto dto){
		return  new ResponseEntity<>(feedbackService.rejectFeedback(tutorId, feedbackId, dto), HttpStatus.OK);
	}
}
