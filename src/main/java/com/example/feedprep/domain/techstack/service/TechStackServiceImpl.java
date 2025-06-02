package com.example.feedprep.domain.techstack.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.feedprep.domain.techstack.dto.CreateTechStackRequestDto;
import com.example.feedprep.domain.techstack.entity.TechStack;
import com.example.feedprep.domain.techstack.repository.TechStackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechStackServiceImpl implements TechStackService{

	private final TechStackRepository techStackRepository;

	@Override
	public void createTechStack(CreateTechStackRequestDto requestDto) {
		TechStack techStack = new TechStack(requestDto.getTechStack());
		techStackRepository.save(techStack);
	}

	@Transactional
	@Override
	public void deleteTechStack(Long techId) {
		TechStack techStack = techStackRepository.findByIdOrElseThrow(techId);
		techStackRepository.delete(techStack);
	}
}
