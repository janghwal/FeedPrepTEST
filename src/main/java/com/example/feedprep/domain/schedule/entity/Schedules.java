package com.example.feedprep.domain.schedule.entity;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.example.feedprep.common.entity.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Schedules")
@Getter
public class Schedules extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scheduleId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private DayOfWeek dayOfWeek;

/*	@ManyToOne
	private Users user;*/
}
