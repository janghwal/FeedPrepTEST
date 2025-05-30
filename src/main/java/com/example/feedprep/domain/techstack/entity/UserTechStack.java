package com.example.feedprep.domain.techstack.entity;

import com.example.feedprep.common.entity.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "UserTechStack")
@Getter
public class UserTechStack extends BaseTimeEntity {
	@Id
	private Long relationId;

	// @ManyToOne
	// private Users user;

	@ManyToOne
	private TechStack techStack;
}
