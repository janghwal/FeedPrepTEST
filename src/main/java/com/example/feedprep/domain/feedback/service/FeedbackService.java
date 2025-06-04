package com.example.feedprep.domain.feedback.service;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.feedback.dto.request.FeedbackRequestDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestListResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackRequestResponseDto;
import com.example.feedprep.domain.feedback.dto.response.FeedbackResponse;

public interface FeedbackService {

  //요청 리스트 조회 단건
   FeedbackRequestResponseDto getFeedbackRequest(Long userId, Long requestId);
  //요청 리스트 조회 다건
   FeedbackRequestListResponseDto getFeedbackRequestList(Long userId , int page, int size);
  //요청 리스트 생성
  FeedbackResponse createFeedback(Long userId, Long requestId, FeedbackRequestDto dto);
  //요청 리스트 수정
  FeedbackResponse updateFeedback(Long userId, Long requestId, FeedbackRequestDto dto);
  //요청 리스트 거절
  ApiResponseDto rejectFeedback(Long userId, Long requestId,  FeedbackRequestDto dto);

}
