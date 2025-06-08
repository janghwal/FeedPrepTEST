package com.example.feedprep.domain.feedbackrequestentity.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;

public interface FeedbackRequestService {
	 //피드백 신청
     FeedbackRequestEntityResponseDto saveRequest(FeedbackRequestDto dto, Long userId);
	 //피드백 신청 조회
	 List<FeedbackRequestEntityResponseDto> getRequest(
		Long userId,
		Long tutorId,
		Long documentId,
		LocalDateTime month,
		int page,
		int size
	 );
	 //피드백 신청 수정
	 FeedbackRequestEntityResponseDto updateRequest(FeedbackRequestDto dto,Long feedbackId, Long userId);
	 //피드백 신청 취소
	 ApiResponseDto cancleRequest(Long RequestId, Long userId);
}
