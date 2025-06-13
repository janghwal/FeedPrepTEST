package com.example.feedprep.domain.techstack.service;

import java.util.List;

import com.example.feedprep.domain.techstack.dto.CreateTechStackRequestDto;
import com.example.feedprep.domain.techstack.dto.TechStackResponseDto;

public interface TechStackService {

	List<TechStackResponseDto> getTechStackList();

	void addTechStack(Long requesterId, Long techId);

	List<TechStackResponseDto> getMyTechStackList(Long requesterId);

	void removeTechStack(Long requesterId, Long relationId);
}
