package com.example.feedprep.domain.feedbackreview.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedbackReviewRequestDto {

	private Long feedbackId;

	@Min(value = 0)
	@Max(value = 5)
	private int rating;

	private String content;
}
