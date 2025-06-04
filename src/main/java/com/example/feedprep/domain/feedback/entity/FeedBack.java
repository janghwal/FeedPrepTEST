package com.example.feedprep.domain.feedback.entity;

import jakarta.persistence.Column;
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
import com.example.feedprep.domain.feedback.common.RejectReason;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;

@Getter
@Entity
@Table(name = "feedbacks")
@RequiredArgsConstructor
public class FeedBack extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name="request_id")
	private FeedbackRequestEntity feedbackRequestEntity;

    @Column(nullable = false)
	private String content;

	private RejectReason rejectReason;

	private String etcContent;

	public FeedBack(FeedbackRequestEntity feedBackRequest, String content) {
		this.feedbackRequestEntity = feedBackRequest;
		this.content = content;
	}
}
