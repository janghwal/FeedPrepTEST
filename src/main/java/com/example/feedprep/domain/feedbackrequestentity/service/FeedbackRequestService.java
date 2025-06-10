package com.example.feedprep.domain.feedbackrequestentity.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRejectRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.TutorSideFeedbackRequestDto;

public interface FeedbackRequestService {
	// 피드백 신청 생성
	FeedbackRequestEntityResponseDto createRequest(Long userId, FeedbackRequestDto dto);

	// 피드백 신청 조회
	List<FeedbackRequestEntityResponseDto> getRequests(
		Long userId,         // 신청자 or 튜터
		Long tutorId,        // 피드백 받을 대상
		Long documentId,     // 문서
		LocalDateTime month, // 월별 필터
		RequestState requestState,
		int page,
		int size
	);
	// 피드백 요청 단건 상세 조회(튜터)
	TutorSideFeedbackRequestDto getFeedbackRequest(Long tutorId, Long requestId);

	// 피드백 요청 다건 조회 (튜터)
	List<TutorSideFeedbackRequestDto> getFeedbackRequests(Long tutorId, int page, int size);

	// 피드백 신청 수정
	FeedbackRequestEntityResponseDto updateRequest(Long userId, Long feedbackRequestId, FeedbackRequestDto dto);

	// 피드백 신청 취소
	ApiResponseDto cancelRequest(Long userId, Long feedbackRequestId);

	// 피드백 요청 거절(튜터)
	ApiResponseDto rejectFeedbackRequest(Long tutorId, Long requestId, int rejectNumber,  FeedbackRejectRequestDto dto);
}
