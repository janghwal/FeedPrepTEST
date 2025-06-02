package com.example.feedprep.domain.feedbackrequestentity.service;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;

public interface FeedbackService {
     FeedbackRequestEntityResponseDto saveRequest(FeedbackRequestDto dto, Long userId);
     FeedbackRequestEntityResponseDto getRequest(Long userId, Long tutorId, Long documentId, int page, int size, LocalDateTime time);
	 FeedbackRequestEntityResponseDto updateRequest(FeedbackRequestDto dto,Long feedbackId, Long userId);
	 ApiResponseDto cancleRequest(Long RequestId, Long userId);
}
