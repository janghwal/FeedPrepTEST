package com.example.feedprep.domain.techstack.entity;

import com.example.feedprep.domain.techstack.dto.CreateTechStackRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TechStack")
@Getter
@NoArgsConstructor
public class TechStack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long techId;
	private String techStack;

	public TechStack(CreateTechStackRequestDto requestDto) {
		this.techStack = requestDto.getTechStack();
	}

	public TechStack(String techStack) {
		this.techStack = techStack;
	}
}
