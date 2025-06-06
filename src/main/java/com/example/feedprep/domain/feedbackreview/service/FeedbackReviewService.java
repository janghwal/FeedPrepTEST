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
	//학생이 직접 작성한 리뷰 모음
	List<FeedbackReviewResponseDto> getWrittenReviewsByStudent(Long userId, int page, int size);
	//튜터가 학생들에게 받은 리뷰를 확인
	List<FeedbackReviewResponseDto> getReceivedReviewsForTutor(Long tutorId, int page, int size);
	//튜터 평점 조회
	Double getAverageRating(Long tutorId);
	//리뷰 수정
	FeedbackReviewResponseDto updateReview( Long userId, Long reviewId, FeedbackReviewRequestDto dto);
	//리뷰의 삭제
	ApiResponseDto deleteReview(Long userId, Long feedbackId, Long reviewId);
}
