package com.example.feedprep.domain.techstack.service;

import java.util.List;

import com.example.feedprep.domain.techstack.dto.CreateTechStackRequestDto;
import com.example.feedprep.domain.techstack.dto.TechStackResponseDto;

public interface TechStackService {
	void createTechStack(CreateTechStackRequestDto requestDto);

	void deleteTechStack(Long techId);

	List<TechStackResponseDto> getTechStackList();

	void addTechStack(Long requesterId, Long techId);

	List<TechStackResponseDto> getMyTechStackList(Long requesterId);

	void removeTechStack(Long relationId);
}
