package com.example.feedprep.domain.techstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.techstack.entity.TechStack;

public interface TechStackRepository extends JpaRepository<TechStack, Long> {
	default TechStack findByIdOrElseThrow(Long techId) {
		return findById(techId).orElseThrow(() -> new CustomException(ErrorCode.TECH_STACK_NOT_FOUND));
	}
}
