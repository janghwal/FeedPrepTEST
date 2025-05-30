package com.example.feedprep.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.domain.schedule.entity.Schedules;

public interface ScheduleRepository extends JpaRepository<Schedules, Long> {
}
