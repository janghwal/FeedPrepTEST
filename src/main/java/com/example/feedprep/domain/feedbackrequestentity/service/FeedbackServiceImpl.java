package com.example.feedprep.domain.feedbackrequestentity.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.document.entity.Document;
import com.example.feedprep.domain.document.repository.DocumentRepository;
import com.example.feedprep.domain.feedback.repository.FeedBackRepository;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedbackrequestentity.dto.response.FeedbackRequestEntityResponseDto;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;

@Service
@Repository
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{
	private final FeedBackRepository feedBackRepository;
	private final UserRepository userRepository;
	private final DocumentRepository documentRepository;

	@Transactional
	@Override
	public FeedbackRequestEntityResponseDto saveRequest(FeedbackRequestDto dto, Long userId) {
		//유저, 튜터 동시 조회.
		List<User> users = userRepository.findByIds(userId,dto.getTutorId());
		//아무것도 없다면 빈 map
		Map<Long, User> userMap = users.stream()
			.collect(Collectors.toMap(User::getUserId, Function.identity()));
        //검증은 여기서 부터
		User user = Optional.ofNullable(userMap
			.get(userId))
			.orElseThrow(
				()-> new CustomException(ErrorCode. UNAUTHORIZED_REQUESTER_ACCESS));

		User tutor = Optional.ofNullable(userMap.get(dto.getTutorId()))
			.orElseThrow(()->new CustomException(ErrorCode.TUTOR_NOT_FOUND));

		Document document = documentRepository.findById(dto.getDocumentId())
			.orElseThrow(()-> new CustomException(ErrorCode.INVALID_DOCUMENT));

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
	@Transactional
	@Override
	public FeedbackRequestEntityResponseDto updateRequest(FeedbackRequestDto dto,Long feedbackId, Long userId) {
		//유저 본인 확인
		User user =  userRepository.findById(dto.getTutorId()).orElseThrow(()-> new CustomException(ErrorCode.INVALID_TUTOR));
		if(!user.getUserId().equals(userId)){
			//예외 반환
		}
		//요청이 존재하는 가?
		FeedbackRequestEntity request = feedBackRepository.findById(feedbackId).orElseThrow(()->new CustomException(ErrorCode.FEEDBACK_NOT_FOUND));
		if(request.getUser().getUserId().equals(userId))
		{

		}
		//수정 적용
		return null;
	}
	@Transactional
	@Override
	public ApiResponseDto cancleRequest(Long RequestId, Long userId) {
		//유저 본인 확인
		User user =  userRepository.findById(dto.getTutorId()).orElseThrow(()-> new CustomException(ErrorCode.INVALID_TUTOR));
		if(!user.getUserId().equals(userId)){
			//예외 반환
		}
		//요청이 존재하는 가?
		FeedbackRequestEntity request = feedBackRepository.findById(feedbackId).orElseThrow(()->new CustomException(ErrorCode.FEEDBACK_NOT_FOUND));
		if(request.getUser().getUserId().equals(userId))
		{

		}
		//취소 적용
		return null;
	}
}
