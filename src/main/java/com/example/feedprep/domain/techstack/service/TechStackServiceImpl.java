package com.example.feedprep.domain.techstack.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.techstack.dto.CreateTechStackRequestDto;
import com.example.feedprep.domain.techstack.dto.TechStackResponseDto;
import com.example.feedprep.domain.techstack.entity.TechStack;
import com.example.feedprep.domain.techstack.entity.UserTechStack;
import com.example.feedprep.domain.techstack.repository.TechStackRepository;
import com.example.feedprep.domain.techstack.repository.UserTechStackRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechStackServiceImpl implements TechStackService{

	private final UserTechStackRepository userTechStackRepository;
	private final TechStackRepository techStackRepository;
	private final UserRepository userRepository;

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

	@Override
	public List<TechStackResponseDto> getTechStackList() {

		List<TechStack> allTechStack = techStackRepository.findAll();

		return allTechStack.stream().map(TechStackResponseDto::new).toList();
	}

	@Override
	public void addTechStack(Long requesterId, Long techId) {
		User requester = userRepository.findByIdOrElseThrow(requesterId);
		TechStack techStack = techStackRepository.findByIdOrElseThrow(techId);

		if(userTechStackRepository.existsByTechStackAndUser(techStack, requester)){
			throw new CustomException(ErrorCode.DUPLICATE_TECH_STACK);
		}

		UserTechStack userTechStack = new UserTechStack(requester, techStack);

		userTechStackRepository.save(userTechStack);
	}

	@Override
	public List<TechStackResponseDto> getMyTechStackList(Long requesterId) {
		List<TechStack> myTechStack = techStackRepository.findTechStackByTechId(requesterId);

		return myTechStack.stream().map(TechStackResponseDto::new).toList();
	}

	@Transactional
	@Override
	public void removeTechStack(Long requesterId, Long relationId) {
		UserTechStack myTechStack = userTechStackRepository.findByIdOrElseThrow(relationId);

		if(!myTechStack.getUser().getUserId().equals(requesterId)){
			throw new CustomException(ErrorCode.UNAUTHORIZED_TECH_STACK_DELETION);
		}

		userTechStackRepository.delete(myTechStack);
	}

}
