package com.example.feedprep.domain.feedback.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;
import com.example.feedprep.domain.user.entity.User;

@Getter
@RequiredArgsConstructor
public class FeedbackRequestListResponseDto {
	private Long tutorId;
	private String nickname;
	private List<FeedbackRequestEntityResponseDto> requests;

	public FeedbackRequestListResponseDto(User user, List<FeedbackRequestEntityResponseDto> requests) {
		this.tutorId = user.getUserId();
		this.nickname = user.getName();
		this.requests = requests;
	}
}
