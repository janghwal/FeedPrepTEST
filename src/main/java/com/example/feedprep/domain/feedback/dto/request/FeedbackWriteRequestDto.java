package com.example.feedprep.domain.feedback.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.example.feedprep.domain.feedback.common.RejectReason;

@Getter
@AllArgsConstructor
public class FeedbackWriteRequestDto {
	private Long feedbackRequestEntityId;
	private String content;
	private RejectReason rejectReason;
	private String etcContent;
}
