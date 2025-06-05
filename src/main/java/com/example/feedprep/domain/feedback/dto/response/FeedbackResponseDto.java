package com.example.feedprep.domain.feedback.dto.response;
import lombok.Getter;

import com.example.feedprep.domain.feedback.entity.Feedback;

@Getter
public class FeedbackResponseDto {

	private Long id;
	private Long feedbackRequestEntityId;
	private String content;

	public FeedbackResponseDto(Feedback feedBack){
		this.id = feedBack.getId();
		this.feedbackRequestEntityId = feedBack.getFeedbackRequestEntity().getId();
		this.content = feedBack.getContent();

	}
}
