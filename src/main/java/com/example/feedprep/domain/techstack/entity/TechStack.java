package com.example.feedprep.domain.techstack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "TechStack")
@Getter
public class TechStack {
	@Id
	private Long techId;
	private String techStack;
}
