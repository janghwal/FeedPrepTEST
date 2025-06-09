package com.example.feedprep.domain.feedback.dto.response;
import java.time.format.DateTimeFormatter;

import lombok.Getter;

import com.example.feedprep.domain.feedback.entity.Feedback;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
public class FeedbackResponseDto {

	private Long id;
	private Long studentId;
	private Long feedbackRequestEntityId;
	private String content;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String modifiedAt;

	public FeedbackResponseDto(Feedback feedBack){
		this.id = feedBack.getId();
		this.studentId = feedBack.getFeedbackRequestEntity().getUser().getUserId();
		this.feedbackRequestEntityId = feedBack.getFeedbackRequestEntity().getId();
		this.content = feedBack.getContent();
		this.modifiedAt = feedBack.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
