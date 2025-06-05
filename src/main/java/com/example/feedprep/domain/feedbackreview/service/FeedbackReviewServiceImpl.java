package com.example.feedprep.domain.feedbackreview.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedback.entity.Feedback;
import com.example.feedprep.domain.feedback.repository.FeedBackRepository;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewRequestDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewResponseDto;
import com.example.feedprep.domain.feedbackreview.entity.FeedbackReview;
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
		User user = userRepository.findByIdOrElseThrow(userId);
		if(!user.getUserId().equals(userId)){
			throw new CustomException(ErrorCode.UNAUTHORIZED_REQUESTER_ACCESS);
		}
		Feedback feedback = feedBackRepository.findById(feedbackId)
			.orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_FEEDBACK));

		FeedbackReview feedbackReview = new FeedbackReview(dto, feedback, user);
		FeedbackReview saveReview = feedBackReviewRepository.save(feedbackReview);

	    return new FeedbackReviewResponseDto(saveReview);
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
