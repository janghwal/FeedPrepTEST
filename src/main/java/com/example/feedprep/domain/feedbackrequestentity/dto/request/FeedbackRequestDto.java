package com.example.feedprep.domain.feedbackrequestentity.dto.request;

import lombok.Getter;

@Getter
public class FeedbackRequestDto {
	private Long tutorId;
	private Long documentId;
	private String content;
}
