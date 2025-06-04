package com.example.feedprep.domain.techstack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.techstack.dto.CreateTechStackRequestDto;
import com.example.feedprep.domain.techstack.service.TechStackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/tech-stacks")
@RequiredArgsConstructor
public class AdminTechStackController {

	private final TechStackService techStackService;

	@PostMapping
	public ResponseEntity<ApiResponseDto<Void>> createTechStack(
		@RequestBody CreateTechStackRequestDto requestDto
	) {

		techStackService.createTechStack(requestDto);

		return ResponseEntity.status(SuccessCode.TECH_STACK_CREATED.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.TECH_STACK_CREATED));
	}

	@DeleteMapping("/{techId}")
	public ResponseEntity<ApiResponseDto<Void>> deleteTechStack(
		@PathVariable Long techId
	) {

		techStackService.deleteTechStack(techId);

		return ResponseEntity.status(SuccessCode.TECH_STACK_DELETED.getHttpStatus())
			.body(ApiResponseDto.success(SuccessCode.TECH_STACK_DELETED));
	}
}
