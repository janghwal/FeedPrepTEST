package com.example.feedprep.domain.techstack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.techstack.entity.TechStack;
import com.example.feedprep.domain.techstack.entity.UserTechStack;

public interface UserTechStackRepository extends JpaRepository<UserTechStack, Long> {
	@Override
	Optional<UserTechStack> findById(Long aLong);

	default UserTechStack findByIdOrElseThrow(Long relationId) {
		return findById(relationId)
			.orElseThrow(() -> new CustomException(ErrorCode.TECH_STACK_NOT_FOUND));
	}
}
