package com.example.feedprep.domain.feedbackreview.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackReviewRequestDto {

	@Min(value = 0L)
	@Max(value = 5L)
	private Integer rating;

	private String content;
}
