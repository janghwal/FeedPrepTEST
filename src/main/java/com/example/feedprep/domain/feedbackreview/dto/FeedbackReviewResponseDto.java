package com.example.feedprep.domain.feedbackreview.dto;

import java.time.format.DateTimeFormatter;

import lombok.Getter;

import com.example.feedprep.domain.feedback.entity.Feedback;
import com.example.feedprep.domain.feedbackreview.entity.FeedbackReview;
import com.example.feedprep.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
public class FeedbackReviewResponseDto {

	private Long id;

	private Long userId;

	private Long tutorId;

	private int Rating;

	private  String Content;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String modifiedAt;

	public FeedbackReviewResponseDto(FeedbackReview feedbackReview) {
		this.id = feedbackReview.getId();
		this.userId = feedbackReview.getUserId();
		this.tutorId =feedbackReview.getTutorId();
		this.Rating = feedbackReview.getRating();
		this.Content = feedbackReview.getContent();
	    this.modifiedAt = feedbackReview.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
