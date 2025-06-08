package com.example.feedprep.domain.feedback.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;
import com.example.feedprep.domain.user.entity.User;

@Getter
@RequiredArgsConstructor
public class FeedbackRequestResponseDto {

	private Long requestId;
	private Long tutorId;
	private Long studentId;
	private Long documentId;
	private String fileUrl;
	private String content;
	private RequestState requestState;
	private String ModifiedAt;

	public FeedbackRequestResponseDto(User user, FeedbackRequestEntity requests) {
		this.requestId = requests.getId();
		this.tutorId = requests.getTutor().getUserId();
		this.studentId = requests.getUser().getUserId();
		this.documentId = requests.getDocument().getDocumentId();
		this.fileUrl =  requests.getDocument().getFileUrl();
		this.content =  requests.getContent();
		this.requestState = requests.getRequestState();
		this.ModifiedAt = requests.getModifiedAt().toString();
	}

}
