package com.example.feedprep.domain.feedbackrequestentity.service;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
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
		//유저 본인 확인
		User user =  userRepository.findById(dto.getTutorId()).orElseThrow(()-> new CustomException(ErrorCode.INVALID_TUTOR));
		if(!user.getUserId().equals(userId)){
			//예외 반환
		}
		//필수 확인
		User tutor = userRepository.findById(dto.getTutorId()).orElseThrow(()-> new CustomException(ErrorCode.INVALID_TUTOR));
		Document document = documentRepository.findById(dto.getDocumentId()).orElseThrow(()-> new CustomException(ErrorCode.INVALID_DOCUMENT));
		if(tutor.getUserId().equals(userId))
		{
			//본인애게 요청하는 경우는 없음
		}

		//확인 후 요청 생성
		FeedbackRequestEntity request = new FeedbackRequestEntity(dto, tutor, document);
		FeedbackRequestEntity getInfoRequest =feedBackRepository.save(request);
		return new FeedbackRequestEntityResponseDto(getInfoRequest);
	}

	@Transactional(readOnly = true)
	@Override
	public FeedbackRequestEntityResponseDto getRequest(Long userId,Long tutorId, Long documentId, Long page, Long size,
		LocalDateTime time) {
		//유저 본인 확인
		User user =  userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.INVALID_TUTOR));
		if(!user.getUserId().equals(userId)){
			//예외 반환
		}
		//조건에 맞게 조회 출력후 무조건 DESC로 할것.
		return null;
	}

	@Override
	public FeedbackRequestEntityResponseDto updateRequest(FeedbackRequestDto dto,Long feedbackId, Long userId) {
		//유저 본인 확인
		//요청이 존재하는 가?
		//수정 적용
		return null;
	}

	@Override
	public ApiResponseDto cancleRequest(Long RequestId, Long userId) {
		//유저 본인 확인
		//요청이 존재하는 가?
		//수정 적용
		return null;
	}
}
