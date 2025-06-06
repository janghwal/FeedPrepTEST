package com.example.feedprep.domain.feedback.service;

import com.example.feedprep.domain.feedback.dto.request.FeedbackWriteRequestDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRejectResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestListResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackResponseDto;

public interface FeedbackService {

  // 피드백 요청 단건 조회
  FeedbackRequestResponseDto getFeedbackRequest(Long tutorId, Long requestId);

  // 피드백 요청 다건 조회 (튜터 기준 페이징)
  FeedbackRequestListResponseDto getFeedbackRequests(Long tutorId, int page, int size);

  // 피드백 생성 (튜터가 요청에 응답)
  FeedbackResponseDto createFeedback(Long tutorId, Long requestId, FeedbackWriteRequestDto dto);

  // 피드백 수정
  FeedbackResponseDto updateFeedback(Long tutorId, Long feedbackId, FeedbackWriteRequestDto dto);

  // 피드백 요청 거절
  FeedbackRejectResponseDto rejectFeedback(Long tutorId, Long feedbackId, FeedbackWriteRequestDto dto);

}
