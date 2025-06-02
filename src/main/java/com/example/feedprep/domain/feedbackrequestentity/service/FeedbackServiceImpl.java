package com.example.feedprep.domain.feedbackrequestentity.service;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;

public class FeedbackServiceImpl implements FeedbackService{
	@Override
	public FeedbackRequestEntityResponseDto saveRequest(FeedbackRequestDto dto, Long userId) {


		return null;
	}

	@Override
	public FeedbackRequestEntityResponseDto getRequest(Long tutorId, Long documentId, Long page, Long size,
		LocalDateTime time) {
		return null;
	}

	@Override
	public FeedbackRequestEntityResponseDto updateRequest(FeedbackRequestDto dto, Long userId) {
		return null;
	}

	@Override
	public ApiResponseDto cancleRequest(Long RequestId, Long userId) {
		return null;
	}
}
