package com.example.feedprep.domain.feedbackreview.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedback.entity.Feedback;
import com.example.feedprep.domain.feedback.repository.FeedBackRepository;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewRequestDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewResponseDto;
import com.example.feedprep.domain.feedbackreview.repository.FeedBackReviewRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class FeedbackReviewServiceImpl implements FeedbackReviewService {
	private final FeedBackReviewRepository feedBackReviewRepository;
	private final FeedBackRepository feedBackRepository;
    private final UserRepository userRepository;
	@Override
	public FeedbackReviewResponseDto saveReview(FeedbackReviewRequestDto dto, Long userId, Long feedbackId) {
	    return null;
	}

	@Override
	public FeedbackReviewResponseDto getReview(FeedbackReviewRequestDto dto, Long userId, Long feedbackId) {
		return null;
	}

	@Override
	public FeedbackReviewResponseDto updateReview(FeedbackReviewRequestDto dto, Long userId, Long feedbackId) {
		return null;
	}

	@Override
	public ApiResponseDto deleteReview(FeedbackReviewRequestDto dto, Long userId, Long feedbackId) {
		return null;
	}
}
