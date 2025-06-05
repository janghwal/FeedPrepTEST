package com.example.feedprep.domain.feedbackreview.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.feedprep.common.entity.BaseTimeEntity;
import com.example.feedprep.domain.feedback.entity.Feedback;
import com.example.feedprep.domain.feedbackreview.dto.FeedbackReviewRequestDto;
import com.example.feedprep.domain.user.entity.User;

@Getter
@Entity
@Table(name = "feedbackreviews")
@RequiredArgsConstructor
public class FeedbackReview  extends  BaseTimeEntity{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@OneToOne
		@JoinColumn(name = "feedback_id",unique = true)
		private Feedback feedback;

		private Long userId;

		private Long tutorId;

		private int Rating;

		private  String content;

		private LocalDateTime deletedAt;

	    public FeedbackReview( FeedbackReviewRequestDto dto, Feedback feedback, User user) {
			this.feedback = feedback;
		    this.userId = user.getUserId();
		    this.tutorId = feedback.getTutor().getUserId();
		    this.Rating = dto.getRating();
		    this.content = dto.getContent();
		}
}
