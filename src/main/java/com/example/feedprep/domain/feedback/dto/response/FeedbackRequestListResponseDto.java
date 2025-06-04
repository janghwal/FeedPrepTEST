package com.example.feedprep.domain.feedback.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.example.feedprep.domain.user.entity.User;

@Getter
@RequiredArgsConstructor
public class FeedbackRequestListResponseDto {
	private Long tutorId;
	private String nickname;
	private List<FeedbackRequestResponseDto> requests;

	public FeedbackRequestListResponseDto(User user, List<FeedbackRequestResponseDto> requests) {
		this.tutorId = user.getUserId();
		this.nickname = user.getName();
		this.requests = requests;
	}
}
