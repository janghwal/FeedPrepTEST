package com.example.feedprep.domain.techstack.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.common.security.jwt.CustomUserDetails;
import com.example.feedprep.domain.techstack.dto.TechStackResponseDto;
import com.example.feedprep.domain.techstack.service.TechStackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tech-stacks")
@RequiredArgsConstructor
public class TechStackController {

	private final TechStackService techStackService;

	@GetMapping
	public ResponseEntity<ApiResponseDto<List<TechStackResponseDto>>> getTechStackList() {

		List<TechStackResponseDto> responseDtoList = techStackService.getTechStackList();

		return ResponseEntity.status(SuccessCode.TECH_STACK_LISTED.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.TECH_STACK_LISTED, responseDtoList));
	}

	@PostMapping
	public ResponseEntity<ApiResponseDto<Void>> addTechStack(
		@AuthUser Long requesterId,
		@RequestParam Long techId
	) {
		techStackService.addTechStack(requesterId, techId);

		return ResponseEntity.status(SuccessCode.ADD_MY_TECH_STACK.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.ADD_MY_TECH_STACK));
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponseDto<List<TechStackResponseDto>>> getMyTechStackList(
		@AuthUser Long requesterId
	) {
		List<TechStackResponseDto> myTechStackList = techStackService.getMyTechStackList(requesterId);

		return ResponseEntity.status(SuccessCode.TECH_STACK_LISTED.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.TECH_STACK_LISTED, myTechStackList));
	}

	@DeleteMapping("/{relationId}")
	public ResponseEntity<ApiResponseDto<Void>> removeTechStack(
		@AuthUser Long requesterId,
		@PathVariable Long relationId) {
		techStackService.removeTechStack(requesterId, relationId);

		return ResponseEntity.status(SuccessCode.REMOVE_MY_TECH_STACK.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.REMOVE_MY_TECH_STACK));
	}

}
