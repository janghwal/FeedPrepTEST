package com.example.feedprep.domain.feedbackreview.controller;

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
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewRequestDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewResponseDto;
import com.example.feedprep.domain.feedbackreview.service.FeedbackReviewService;

@RestController
@RequiredArgsConstructor
public class FeedbackReviewController {
	private final FeedbackReviewService feedbackReviewService;

	@PostMapping("/feedbacks/{feedbackId}/reviews")
	public ResponseEntity<FeedbackReviewResponseDto> createReview(
		@AuthUser Long userId,
		@PathVariable Long feedbackId,
		@RequestBody FeedbackReviewRequestDto dto){
		return  new ResponseEntity<>(feedbackReviewService.createReview(userId, feedbackId, dto), HttpStatus.CREATED);
	}
	@GetMapping("/users/me/reviews")
	public ResponseEntity<List<FeedbackReviewResponseDto>> getWrittenReviewsByStudent(
		@AuthUser Long userId,
		@RequestParam(defaultValue= "0") int page,
		@RequestParam(defaultValue = "20") int size){
		return new ResponseEntity<>(feedbackReviewService.getWrittenReviewsByStudent(userId, page, size), HttpStatus.OK);
	}
	@GetMapping("/users/me/reviews/received")
	public ResponseEntity<List<FeedbackReviewResponseDto>> getReceivedReviewsForTutor(
		@AuthUser Long tutorId,
		@RequestParam(defaultValue= "0") int page,
		@RequestParam(defaultValue = "20") int size){
		return new ResponseEntity<>(feedbackReviewService.getReceivedReviewsForTutor(tutorId, page, size), HttpStatus.OK);
	}


	@PatchMapping("/reviews/{reviewId}")
	public ResponseEntity<FeedbackReviewResponseDto> updateReview(
		@AuthUser Long userId,
		@PathVariable Long reviewId,
		@RequestBody FeedbackReviewRequestDto dto){
		return  new ResponseEntity<>(feedbackReviewService.updateReview(userId, reviewId, dto), HttpStatus.OK);
	}
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<ApiResponseDto> deleteReview(
		@AuthUser Long userId,
		@PathVariable Long reviewId
	){
	return  new ResponseEntity<>(feedbackReviewService.deleteReview(userId, reviewId), HttpStatus.OK);
	}
}
