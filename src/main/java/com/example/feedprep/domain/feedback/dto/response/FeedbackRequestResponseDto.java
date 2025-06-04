package com.example.feedprep.domain.feedback.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;
import com.example.feedprep.domain.user.entity.User;

@Getter
@RequiredArgsConstructor
public class FeedbackRequestResponseDto {
	private Long tutorId;
	private String nickname;
	private FeedbackRequestEntity requests;

	public FeedbackRequestResponseDto(User user, FeedbackRequestEntity requests) {
		this.tutorId = user.getUserId();
		this.nickname = user.getName();
		this.requests = requests;
	}
}
