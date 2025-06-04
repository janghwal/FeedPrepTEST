package com.example.feedprep.domain.feedback.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedback.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackResponse;
import com.example.feedprep.domain.feedback.entity.Feedback;
import com.example.feedprep.domain.feedback.repository.FeedBackRepository;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;
import com.example.feedprep.domain.feedbackrequestentity.repository.FeedbackRequestEntityRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{
	private final FeedBackRepository feedBackRepository;
	private final FeedbackRequestEntityRepository feedbackRequestEntityRepository;
	private final UserRepository userRepository;
	@Transactional
	@Override
	public FeedbackResponse createFeedback(Long userId, Long requestId, FeedbackRequestDto dto) {
		// 1. 유효성 검사 (필수 필드, 글자 수 등)
		// 3. 튜터 권한 확인
		User tutor = userRepository.findByIdOrElseThrow(userId);
		// 2. 피드백 요청 상태 확인 (이미 완료/거절된 요청인지)
		FeedbackRequestEntity request = feedbackRequestEntityRepository.findPendingByIdAndTutorOrElseThrow(
			tutor.getUserId(), requestId, ErrorCode.USER_NOT_FOUND);
		if(request.getRequestState().equals(RequestState.CANCELED)
			|| request.getRequestState().equals(RequestState.COMPLETED)){
			throw new RuntimeException("현재 요청이 취소 되었거나 완료가되었습니다.");
		}
		// 4. Feedback 엔티티 생성
		Feedback feedback = new Feedback(dto);
		request.updateRequestState(RequestState.COMPLETED);
		feedback.updateFeedbackRequest(request);
		// 6. 저장 (트랜잭션 내에서)
		Feedback saveFeedback = feedBackRepository.save(feedback);
		// 7. 알림 or 이벤트 발행 (선택)

		// 8. Response 반환
		return new FeedbackResponse(saveFeedback);
	}


	public FeedbackResponse updateFeedback(Long tutorId, Long feedbackId, FeedbackRequestDto dto) {

	return  null;
	}

	public ApiResponseDto rejectFeedback(Long userId, Long requestId) {
		return  null;
	}
}
