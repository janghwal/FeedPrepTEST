package com.example.feedprep.domain.feedback.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedbackWriteRequestDto {
	@NotNull(message = "피드백 작성하려는 요청 번호를 입력하세요")
	private Long feedbackRequestEntityId;
	@Size(min =10, message = "10글자 이상 입력해주세요.")
	private String content;
}
