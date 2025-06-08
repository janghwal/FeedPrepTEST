package com.example.feedprep.domain.feedback.dto.response;
import lombok.Getter;

import com.example.feedprep.domain.feedback.entity.Feedback;

@Getter
public class FeedbackResponseDto {

	private Long id;
	private Long studentId;
	private Long feedbackRequestEntityId;
	private String content;
	private String modifiedAt;

	public FeedbackResponseDto(Feedback feedBack){
		this.id = feedBack.getId();
		this.studentId = feedBack.getFeedbackRequestEntity().getUser().getUserId();
		this.feedbackRequestEntityId = feedBack.getFeedbackRequestEntity().getId();
		this.content = feedBack.getContent();
		this.modifiedAt = feedBack.getModifiedAt().toString();
	}
}
