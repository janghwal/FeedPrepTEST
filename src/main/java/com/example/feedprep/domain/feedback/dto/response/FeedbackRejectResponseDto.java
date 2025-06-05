package com.example.feedprep.domain.feedback.dto.response;

import lombok.Getter;

import com.example.feedprep.domain.feedback.common.RejectReason;
import com.example.feedprep.domain.feedback.entity.Feedback;

@Getter
public class FeedbackRejectResponseDto {
	private Long id;
	private Long feedbackRequestEntityId;
	private String content;
	private RejectReason rejectReason;
	private String reason;
	private String etcContent;

	public FeedbackRejectResponseDto(Feedback feedBack){
		this.id = feedBack.getId();
		this.feedbackRequestEntityId = feedBack.getFeedbackRequestEntity().getId();
		this.content = feedBack.getContent();
		this.rejectReason = feedBack.getRejectReason();
		this.reason = feedBack.getRejectReason().getDescription();
		this.etcContent = feedBack.getEtcContent();

	}
}
