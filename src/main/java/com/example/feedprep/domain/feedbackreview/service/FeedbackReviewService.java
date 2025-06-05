package com.example.feedprep.domain.feedbackreview.service;

import java.util.List;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewRequestDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewResponseDto;
import com.example.feedprep.domain.feedbackreview.entity.FeedbackReview;

public interface FeedbackReviewService {

	FeedbackReviewResponseDto saveReview(FeedbackReviewRequestDto dto, Long userId, Long feedbackId);
	FeedbackReviewResponseDto getReview(Long userId, Long reviewId);
	List<FeedbackReviewResponseDto> getWrittenReviewsByStudent(Long userId, Long reviewId, int page, int size);
	List<FeedbackReviewResponseDto> getReceivedReviewsForTutor(Long userId, Long reviewId, int page, int size);
	Float getAverageRating(Long tutorId);
	FeedbackReviewResponseDto updateReview(FeedbackReviewRequestDto dto, Long userId, Long reviewId);
	ApiResponseDto deleteReview(Long userId, Long feedbackId, Long reviewId);
}
