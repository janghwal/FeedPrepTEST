package com.example.feedprep.domain.techstack.entity;

import com.example.feedprep.common.entity.BaseTimeEntity;
import com.example.feedprep.domain.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "UserTechStack")
@Getter
public class UserTechStack extends BaseTimeEntity {
	@Id
	private Long relationId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "tech_id")
	private TechStack techStack;
}
