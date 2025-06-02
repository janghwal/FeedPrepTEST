package com.example.feedprep.domain.techstack.service;

import com.example.feedprep.domain.techstack.dto.CreateTechStackRequestDto;

public interface TechStackService {
	void createTechStack(CreateTechStackRequestDto requestDto);

	void deleteTechStack(Long techId);
}
