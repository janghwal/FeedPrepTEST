package com.example.feedprep.domain.feedbackreview.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedbackReviewRequestDto {

	private Long feedbackId;

	@Min(value = 0L)
	@Max(value = 5L)
	private Long rating;

	private String content;
}
