package com.example.feedprep.domain.feedbackrequestentity.service;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.document.repository.DocumentRepository;
import com.example.feedprep.domain.feedback.repository.FeedBackRepository;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;

@Service
@Repository
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{
	private FeedBackRepository feedBackRepository;
	private UserRepository userRepository;
	private DocumentRepository documentRepository;

	@Transactional
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
