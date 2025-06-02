package com.example.feedprep.domain.techstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedprep.domain.techstack.entity.TechStack;

public interface TechStackRepository extends JpaRepository<TechStack, Long> {
}
