package com.example.feedprep.domain.techstack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.techstack.entity.TechStack;
import com.example.feedprep.domain.techstack.entity.UserTechStack;
import com.example.feedprep.domain.user.entity.User;

public interface UserTechStackRepository extends JpaRepository<UserTechStack, Long> {
	default UserTechStack findByIdOrElseThrow(Long relationId) {
		return findById(relationId)
			.orElseThrow(() -> new CustomException(ErrorCode.TECH_STACK_NOT_FOUND));
	}

	List<UserTechStack> findUserTechStackByUser_UserId(Long UserId);

	boolean existsByTechStackAndUser(TechStack techStack, User user);
}
