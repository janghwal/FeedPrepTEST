package com.example.feedprep.domain.feedbackreview.service;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewRequestDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewResponseDto;
import com.example.feedprep.domain.feedbackreview.entity.FeedbackReview;

public interface FeedbackReviewService {

	FeedbackReviewResponseDto saveReview(FeedbackReviewRequestDto dto, Long userId);
	FeedbackReviewResponseDto getReview(FeedbackReviewRequestDto dto, Long userId);
	FeedbackReviewResponseDto updateReview(FeedbackReviewRequestDto dto, Long userId);
	ApiResponseDto deleteReview(FeedbackReviewRequestDto dto, Long userId);
}
