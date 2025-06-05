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

import com.example.feedprep.common.entity.BaseTimeEntity;
import com.example.feedprep.domain.feedback.entity.Feedback;
@Getter
@Entity
@Table(name = "feedbackreviews")
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

		private  String Content;

		private LocalDateTime deletedAt;
}
