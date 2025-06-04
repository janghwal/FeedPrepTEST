package com.example.feedprep.domain.feedback.service;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedback.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackResponse;

public interface FeedbackService {

  FeedbackResponse createFeedback(Long userId, Long requestId, FeedbackRequestDto dto);
  FeedbackResponse updateFeedback(Long userId, Long requestId, FeedbackRequestDto dto);
  ApiResponseDto rejectFeedback(Long userId, Long requestId,  FeedbackRequestDto dto);

}
