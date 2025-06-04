package com.example.feedprep.domain.feedbackrequestentity.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;

@Getter
@RequiredArgsConstructor
public class FeedbackRequestEntityResponseDto {
	private Long id;
	private Long tutorId;
	private Long documentId;
	private String content;
	private RequestState requestState;
	private LocalDateTime modifiedAt;

	public FeedbackRequestEntityResponseDto(FeedbackRequestEntity entity){
		this.id = entity.getId();
		this.tutorId = entity.getUser().getUserId();
		this.documentId = entity.getDocument().getDocumentId();
		this.content = entity.getContent();
		this.requestState = entity.getRequestState();
		this.modifiedAt = entity.getModifiedAt();
	}


}
