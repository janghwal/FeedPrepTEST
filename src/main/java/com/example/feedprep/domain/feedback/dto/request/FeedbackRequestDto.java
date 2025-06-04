package com.example.feedprep.domain.feedback.dto.request;

import lombok.Getter;

import com.example.feedprep.domain.feedback.common.RejectReason;

@Getter
public class FeedbackRequestDto {
	private Long feedbackRequestEntityId;
	private String content;
	private RejectReason rejectReason;
	private String etcContent;
}
