package com.example.feedprep.domain.feedback.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackWriteRequestDto {
	@Size(min =10, message = "10글자 이상 입력해주세요.")
	private String content;
}
