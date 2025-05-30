package com.example.feedprep.domain.schedule.entity;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.example.feedprep.common.entity.BaseTimeEntity;
import com.example.feedprep.domain.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "schedule")
@Getter
public class schedule extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scheduleId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private DayOfWeek dayOfWeek;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
