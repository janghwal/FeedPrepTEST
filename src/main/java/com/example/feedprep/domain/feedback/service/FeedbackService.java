package com.example.feedprep.domain.feedback.service;

import com.example.feedprep.domain.feedback.dto.request.FeedbackWriteRequestDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRejectResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestListResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackResponseDto;

public interface FeedbackService {

  //요청 리스트 조회 단건
   FeedbackRequestResponseDto getFeedbackRequest(Long userId, Long requestId);
  //요청 리스트 조회 다건
  FeedbackRequestListResponseDto getFeedbackRequestList(Long userId , int page, int size);
  //요청 리스트 생성
  FeedbackResponseDto createFeedback(Long userId, Long requestId, FeedbackWriteRequestDto dto);
  //요청 리스트 수정
  FeedbackResponseDto updateFeedback(Long userId, Long requestId, FeedbackWriteRequestDto dto);
  //요청 리스트 거절
  FeedbackRejectResponseDto rejectFeedback(Long userId, Long requestId,  FeedbackWriteRequestDto dto);

}
