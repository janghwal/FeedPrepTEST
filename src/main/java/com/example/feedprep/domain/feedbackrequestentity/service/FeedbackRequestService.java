package com.example.feedprep.domain.feedbackrequestentity.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;

public interface FeedbackRequestService {
	// 피드백 신청 생성
	FeedbackRequestEntityResponseDto createRequest(Long userId, FeedbackRequestDto dto);

	// 피드백 신청 조회
	List<FeedbackRequestEntityResponseDto> getRequests(
		Long userId,         // 신청자 or 튜터
		Long tutorId,        // 피드백 받을 대상
		Long documentId,     // 문서
		LocalDateTime month, // 월별 필터
		int page,
		int size
	);

	// 피드백 신청 수정
	FeedbackRequestEntityResponseDto updateRequest(Long userId, Long feedbackRequestId, FeedbackRequestDto dto);

	// 피드백 신청 취소
	ApiResponseDto cancelRequest(Long userId, Long feedbackRequestId);
}
