package com.example.feedprep.domain.feedbackrequestentity.service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.document.repository.DocumentRepository;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;
import com.example.feedprep.domain.feedbackrequestentity.repository.FeedbackRequestEntityRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;

@Service
@Repository
@RequiredArgsConstructor
public class FeedbackRequestServiceImpl implements FeedbackRequestService {
	private final FeedbackRequestEntityRepository feedbackRequestEntityRepository;
	private final UserRepository userRepository;
	private final DocumentRepository documentRepository;

	@Transactional
	@Override
	public FeedbackRequestEntityResponseDto saveRequest(FeedbackRequestDto dto, Long userId) {
		User user = userRepository.findByIdOrElseThrow(userId);
		User tutor = userRepository.findByIdOrElseThrow(dto.getTutorId(), ErrorCode.NOT_FOUND_TUTOR);

		Document document = documentRepository.findById(dto.getDocumentId())
			.orElseThrow(()-> new CustomException(ErrorCode.INVALID_DOCUMENT));

        FeedbackRequestEntity feedbackRequestEntity =
			 feedbackRequestEntityRepository.findTop1ByUser_UserIdAndTutor_UserIdAndContentAndRequestState(
				 userId,
				 tutor.getUserId(),
				 RequestState.PENDING)
				 .orElse(null);

        if(feedbackRequestEntity != null){
			throw new RuntimeException("이미 같은 튜터님께 신청 대기 중입니다.");
		}

		FeedbackRequestEntity request = new FeedbackRequestEntity(dto, user, tutor, document);
		request.updateRequestState(RequestState.PENDING);
		FeedbackRequestEntity getInfoRequest =feedbackRequestEntityRepository.save(request);
		return new FeedbackRequestEntityResponseDto(getInfoRequest);
	}

	@Transactional(readOnly = true)
	@Override
	public List<FeedbackRequestEntityResponseDto> getRequest(
		Long userId,
		Long tutorId,
		Long documentId,
		LocalDateTime month,
		int page,
		int size
		)
	{
		//유저 본인 확인
		User user = userRepository.findByIdOrElseThrow(userId);
		if(!user.getUserId().equals(userId)){
			throw  new CustomException(ErrorCode. UNAUTHORIZED_REQUESTER_ACCESS);
		}
		PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));


		Page<FeedbackRequestEntity> pages =
			feedbackRequestEntityRepository.findByRequest(userId,tutorId,documentId,month, pageable);

		List<FeedbackRequestEntityResponseDto> result =
			pages.stream().map(FeedbackRequestEntityResponseDto::new).toList();
		return result;
	}

	@Transactional
	@Override
	public FeedbackRequestEntityResponseDto updateRequest(FeedbackRequestDto dto,Long feedbackId, Long userId) {

		//요청이 존재하는 가?
		FeedbackRequestEntity request = feedbackRequestEntityRepository.findById(feedbackId)
			.orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_FEEDBACK_REQUEST));
		if(!request.getUser().getUserId().equals(userId)){
			throw new CustomException(ErrorCode.UNAUTHORIZED_REQUESTER_ACCESS);
		}

		if (request.getRequestState() != RequestState.PENDING) {
			throw new CustomException(ErrorCode.CANNOT_EDIT_COMPLETED_REQUEST);
		}
		User tutor = userRepository.findByIdOrElseThrow(userId);

		//문서 조회
		Document document = documentRepository.findById(dto.getDocumentId())
			.orElseThrow(()-> new CustomException(ErrorCode.INVALID_DOCUMENT));

		request.updateFeedbackRequestEntity(dto, tutor, document);

		return new FeedbackRequestEntityResponseDto(request);
	}


	@Transactional
	@Override
	public ApiResponseDto cancleRequest(Long RequestId, Long userId) {
		//요청이 존재하는 가?
		FeedbackRequestEntity request = feedbackRequestEntityRepository.findById(RequestId)
			.orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_FEEDBACK_REQUEST));
		if(!request.getUser().getUserId().equals(userId))
		{
			throw new CustomException(ErrorCode.UNAUTHORIZED_REQUESTER_ACCESS);
		}
		if (request.getRequestState() != RequestState.PENDING){
			throw new CustomException(ErrorCode.CANNOT_EDIT_COMPLETED_REQUEST);
		}
		request.updateRequestState(RequestState.CANCELED);


		return new ApiResponseDto(
			SuccessCode.OK_FEEDBACK_REQUEST_CANCELED.getHttpStatus().value(),
			SuccessCode.OK_FEEDBACK_REQUEST_CANCELED.getMessage(),
		SuccessCode.OK_FEEDBACK_REQUEST_CANCELED.name());
	}
}
