package com.example.feedprep.domain.feedbackreview.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewRequestDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewResponseDto;
import com.example.feedprep.domain.feedbackreview.service.FeedbackReviewService;

@RestController
@RequiredArgsConstructor
public class FeedbackReviewController {
	private final FeedbackReviewService feedbackReviewService;

	@PostMapping("/feedback/{feedbackId}/review")
	public ResponseEntity<FeedbackReviewResponseDto> createReview(
		@AuthUser Long userId,
		@PathVariable Long feedbackId,
		@Validated @RequestBody FeedbackReviewRequestDto dto){
		return  new ResponseEntity<>(feedbackReviewService.createReview(userId, feedbackId, dto), HttpStatus.CREATED);
	}

	@GetMapping("/feedback/review")
	public ResponseEntity<List<FeedbackReviewResponseDto>> getWrittenReviewsByStudent(
		@AuthUser Long userId,
		@RequestParam(defaultValue= "0") int page,
		@Validated @RequestParam(defaultValue = "20") int size){
		return new ResponseEntity<>(feedbackReviewService.getReviews(userId, page, size), HttpStatus.OK);
	}

	@PatchMapping("/feedback/{feedbackId}/review")
	public ResponseEntity<FeedbackReviewResponseDto> updateReview(
		@AuthUser Long userId,
		@PathVariable Long reviewId,
		@Validated @RequestBody FeedbackReviewRequestDto dto){
		return new ResponseEntity<>(feedbackReviewService.updateReview(userId, reviewId, dto), HttpStatus.OK);
	}
	@DeleteMapping("/feedback/{feedbackId}/review")
	public ResponseEntity<ApiResponseDto> deleteReview(
		@AuthUser Long userId,
		@PathVariable Long reviewId
	){
	return new ResponseEntity<>(feedbackReviewService.deleteReview(userId, reviewId), HttpStatus.OK);
	}
}
