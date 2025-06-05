package com.example.feedprep.domain.feedbackreview.dto;

import lombok.Getter;

import com.example.feedprep.domain.feedback.entity.Feedback;
import com.example.feedprep.domain.feedbackreview.entity.FeedbackReview;
import com.example.feedprep.domain.user.entity.User;

@Getter
public class FeedbackReviewResponseDto {

	private Long id;

	private Long userId;

	private Long tutorId;

	private int Rating;

	private  String Content;

	public FeedbackReviewResponseDto(FeedbackReview feedbackReview) {
		this.id = feedbackReview.getId();
		this.userId = feedbackReview.getUserId();
		this.tutorId =feedbackReview.getTutorId();
		this.Rating = feedbackReview.getRating();
		this.Content = feedbackReview.getContent();
	}
}
