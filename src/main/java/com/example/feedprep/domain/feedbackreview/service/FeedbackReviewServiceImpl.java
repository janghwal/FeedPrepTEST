package com.example.feedprep.domain.feedbackreview.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedback.entity.Feedback;
import com.example.feedprep.domain.feedback.repository.FeedBackRepository;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewRequestDto;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewResponseDto;
import com.example.feedprep.domain.feedbackreview.entity.FeedbackReview;
import com.example.feedprep.domain.feedbackreview.repository.FeedBackReviewRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import com.example.feedprep.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class FeedbackReviewServiceImpl implements FeedbackReviewService {
	private final FeedBackReviewRepository feedBackReviewRepository;
	private final FeedBackRepository feedBackRepository;
    private final UserRepository userRepository;
	@Transactional
	@Override
	public FeedbackReviewResponseDto createReview( Long userId, Long feedbackId, FeedbackReviewRequestDto dto) {
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


	@Transactional(readOnly = true)
	@Override
	public FeedbackReviewResponseDto getReview(Long reviewId, Long userId) {
		User user = userRepository.findByIdOrElseThrow(userId);
		if(!user.getUserId().equals(userId)){
			throw new CustomException(ErrorCode.UNAUTHORIZED_REQUESTER_ACCESS);
		}
		FeedbackReview feedbackReview = feedBackReviewRepository.findById(reviewId)
			.orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_FEEDBACK_REVIEW));
		if(!feedbackReview.getUserId().equals(userId)){
			throw new CustomException(ErrorCode.FOREIGN_REQUESTER_REVIEW_ACCESS);
		}

		return  new FeedbackReviewResponseDto(feedbackReview);
	}
	@Transactional(readOnly = true)
	@Override
	public List<FeedbackReviewResponseDto> getReviews(Long userId, Integer page, Integer size) {

		User user = userRepository.findByIdOrElseThrow(userId);

		PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
		Page<FeedbackReview> reviews = null;
		if(!user.getRole().equals(UserRole.APPROVED_TUTOR))
		{
			if(!user.getUserId().equals(userId)){
				throw new CustomException(ErrorCode.UNAUTHORIZED_REQUESTER_ACCESS);
			}
			reviews =  feedBackReviewRepository.findByUserIdAndDeletedAtIsNull(user.getUserId(),pageable);
		}
        else {
			reviews = feedBackReviewRepository.findByTutorIdAndDeletedAtIsNull(user.getUserId(),pageable);
		}
		return reviews.stream()
			.map(FeedbackReviewResponseDto ::new)
			.collect(Collectors.toList());
	}
	@Transactional
	@Override
	public Double getAverageRating(Long tutorId) {
		User tutor = userRepository.findByIdOrElseThrow(tutorId);
		feedBackReviewRepository.getAverageRating(tutor.getUserId());
		return feedBackReviewRepository.getAverageRating(tutor.getUserId());
	}
	@Transactional
	@Override
	public FeedbackReviewResponseDto updateReview(Long userId, Long reviewId, FeedbackReviewRequestDto dto) {
		User user = userRepository.findByIdOrElseThrow(userId);
		if(!user.getUserId().equals(userId)){
			throw new CustomException(ErrorCode.UNAUTHORIZED_REQUESTER_ACCESS);
		}
		FeedbackReview feedbackReview = feedBackReviewRepository.findById(reviewId)
			.orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_FEEDBACK_REVIEW));
        if(!feedbackReview.getUserId().equals(userId)){
			throw new CustomException(ErrorCode.FOREIGN_REQUESTER_REVIEW_ACCESS);
		}
		feedbackReview.updateFeedbackReview(dto);
		FeedbackReview saveReview = feedBackReviewRepository.save(feedbackReview);
		return new FeedbackReviewResponseDto(saveReview);
	}
	@Transactional
	@Override
	public ApiResponseDto deleteReview(Long userId, Long reviewId) {
		User user = userRepository.findByIdOrElseThrow(userId);
		if(!user.getUserId().equals(userId)){
			throw new CustomException(ErrorCode.UNAUTHORIZED_REQUESTER_ACCESS);
		}
		FeedbackReview feedbackReview = feedBackReviewRepository.findById(reviewId)
			.orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_FEEDBACK_REVIEW));
		if(!feedbackReview.getUserId().equals(userId)){
			throw new CustomException(ErrorCode.FOREIGN_REQUESTER_REVIEW_ACCESS);
		}
		feedbackReview.updateDeletedAt(LocalDateTime.now());
		return new ApiResponseDto(
			SuccessCode.OK_SUCCESS_FEEDBACK_REVIEW_DELETED.getHttpStatus().value(),
			SuccessCode.OK_SUCCESS_FEEDBACK_REVIEW_DELETED.getMessage(),
			SuccessCode.OK_SUCCESS_FEEDBACK_REVIEW_DELETED.getHttpStatus()
			);
	}
}
