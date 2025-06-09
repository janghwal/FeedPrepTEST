package com.example.feedprep.domain.feedbackreview.service;

import java.util.List;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewRequestDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewResponseDto;
import com.example.feedprep.domain.feedbackreview.entity.FeedbackReview;

public interface FeedbackReviewService {

    //리뷰 추가
	FeedbackReviewResponseDto createReview( Long userId, Long feedbackId, FeedbackReviewRequestDto dto);
	//리뷰 단건 조회
	FeedbackReviewResponseDto getReview(Long userId, Long reviewId);

	List<FeedbackReviewResponseDto> getReviews(Long userId, int page, int size);

	//튜터 평점 조회
	Double getAverageRating(Long tutorId);
	//리뷰 수정
	FeedbackReviewResponseDto updateReview( Long userId, Long reviewId, FeedbackReviewRequestDto dto);
	//리뷰의 삭제
	ApiResponseDto deleteReview(Long userId, Long reviewId);
}
