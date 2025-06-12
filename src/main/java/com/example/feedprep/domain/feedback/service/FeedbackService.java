package com.example.feedprep.domain.feedback.service;

import com.example.feedprep.domain.feedback.dto.request.FeedbackWriteRequestDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackResponseDto;

public interface FeedbackService {

  // 피드백 생성 (튜터가 요청에 응답)
  FeedbackResponseDto createFeedback(Long tutorId, Long requestId, FeedbackWriteRequestDto dto);

  // 피드백 수정
  FeedbackResponseDto updateFeedback(Long tutorId, Long feedbackId, FeedbackWriteRequestDto dto);



}
