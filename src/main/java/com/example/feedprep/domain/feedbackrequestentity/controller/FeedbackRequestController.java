package com.example.feedprep.domain.feedbackrequestentity.controller;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.service.FeedbackRequestService;

@RestController
@RequestMapping("requests")
@RequiredArgsConstructor
public class FeedbackRequestController {
    private final FeedbackRequestService feedbackRequestService;

	@PostMapping
	public ResponseEntity<FeedbackRequestEntityResponseDto> createRequest(
		@AuthUser Long userId,
		@RequestBody FeedbackRequestDto dto){

		return new ResponseEntity<>(feedbackRequestService.createRequest(userId, dto), HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<FeedbackRequestEntityResponseDto>> getRequest(
		@AuthUser Long userId,
		@RequestParam(required = false) Long tutorId,
		@RequestParam(required = false) Long documentId,
		@RequestParam(required = false) String inputMonth,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size
	){
		LocalDateTime month = null;
		if(inputMonth != null){
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
			YearMonth ym = YearMonth.parse(inputMonth, dateTimeFormatter);
			month = ym.atDay(1).atStartOfDay();  // LocalDateTime으로 변환
		}
		return new ResponseEntity<>(feedbackRequestService.getRequests(userId, tutorId, documentId,  month, page, size), HttpStatus.OK);
	}

	@PutMapping("{requestId}/update")
	public ResponseEntity<FeedbackRequestEntityResponseDto> updateRequest(
		@AuthUser Long userId,
		@PathVariable Long requestId,
		@RequestBody FeedbackRequestDto dto
	){
		return new ResponseEntity<>(feedbackRequestService.updateRequest(userId,requestId, dto), HttpStatus.OK);
	}
	@DeleteMapping("{requestId}/cancel")
	public ResponseEntity<ApiResponseDto> cancelRequest(
		@AuthUser Long userId,
		@PathVariable Long requestId
	){
		return new ResponseEntity<>(feedbackRequestService.cancelRequest(userId,requestId), HttpStatus.OK);
	}
}
