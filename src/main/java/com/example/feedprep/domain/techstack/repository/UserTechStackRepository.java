package com.example.feedprep.domain.techstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.domain.techstack.entity.UserTechStack;

public interface UserTechStackRepository extends JpaRepository<UserTechStack, Long> {
}
