package com.example.feedprep.domain.techstack.dto;

import com.example.feedprep.domain.techstack.entity.TechStack;

import lombok.Getter;

@Getter
public class TechStackResponseDto {
	private Long techStackId;
	private String techStack;

	public TechStackResponseDto(TechStack techStack) {
		this.techStack = techStack.getTechStack();
		this.techStackId = techStack.getTechId();
	}
}
