package com.example.feedprep.domain.feedbackrequestentity.controller;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRejectRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.TutorSideFeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.service.FeedbackRequestService;

@RestController
@RequestMapping("/feedback-requests")
@RequiredArgsConstructor
public class FeedbackRequestController {
    private final FeedbackRequestService feedbackRequestService;

	@PostMapping
	public ResponseEntity<FeedbackRequestEntityResponseDto> createRequest(
		@AuthUser Long userId,
		@Validated @RequestBody FeedbackRequestDto dto){

		return new ResponseEntity<>(feedbackRequestService.createRequest(userId, dto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<FeedbackRequestEntityResponseDto>> getRequest(
		@AuthUser Long userId,
		@RequestParam(required = false) Long tutorId,
		@RequestParam(required = false) Long documentId,
		@RequestParam(required = false) String inputMonth,
		@RequestParam(required = false) Integer stateNumber,
		@RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "20") Integer size
	){
		LocalDateTime month = null;
		if(inputMonth != null){
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
			YearMonth ym = YearMonth.parse(inputMonth, dateTimeFormatter);
			month = ym.atDay(1).atStartOfDay();  // LocalDateTime으로 변환
		}
		RequestState requestState = null;
		Integer num= stateNumber;
		if(num !=null){
			requestState = RequestState.fromNumber(num);
		}
		return new ResponseEntity<>(feedbackRequestService.getRequests(userId,
			tutorId,
			documentId,
			month,
			requestState,
			page,
			size),
			HttpStatus.OK);
	}

	@PutMapping("/{requestId}")
	public ResponseEntity<FeedbackRequestEntityResponseDto> updateRequest(
		@AuthUser Long userId,
		@PathVariable Long requestId,
		@Validated @RequestBody FeedbackRequestDto dto
	){
		return new ResponseEntity<>(feedbackRequestService.updateRequest(userId,requestId, dto), HttpStatus.OK);
	}
	@DeleteMapping("/{requestId}")
	public ResponseEntity<ApiResponseDto> cancelRequest(
		@AuthUser Long userId,
		@Validated @PathVariable Long requestId
	){
		return new ResponseEntity<>(feedbackRequestService.cancelRequest(userId,requestId), HttpStatus.OK);
	}


	//튜터
	@GetMapping("{requestId}")
	public ResponseEntity<TutorSideFeedbackRequestDto>  getFeedbackRequest(
		@AuthUser Long tutorId,
		@PathVariable  Long requestId
	){
		return  new ResponseEntity<>(feedbackRequestService.getFeedbackRequest(tutorId, requestId), HttpStatus.OK);
	}
	@GetMapping("/tutor")
	public ResponseEntity<List<TutorSideFeedbackRequestDto>> getFeedbackRequests(
		@AuthUser Long tutorId,
		@RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "20") Integer size
	){
		return new ResponseEntity<>(feedbackRequestService.getFeedbackRequests(tutorId, page, size), HttpStatus.OK);
	}

	@PatchMapping("/{requestId}")
	public ResponseEntity<ApiResponseDto> rejectFeedbackRequest(
		@AuthUser Long tutorId,
		@PathVariable Long requestId,
		@RequestParam Integer rejectNumber,
		@Validated @RequestBody FeedbackRejectRequestDto dto){
		return  new ResponseEntity<>(feedbackRequestService.rejectFeedbackRequest(tutorId, requestId, rejectNumber, dto), HttpStatus.OK);
	}
}
