package com.example.feedprep.domain.feedbackrequestentity.common;

import java.util.Arrays;

import lombok.Getter;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;

@Getter
public enum RequestState {

	PENDING(0,"요청 대기중"),
	APPROVED(1,"요청 수락 됨"),
	REJECTED (2,"거절 됨"),
	IN_PROGRESS(3,"피드백 작성 중"),
	COMPLETED(4,"피드백 작성 완료"),
	CANCELED(5,"피드백 요청이 취소됨");
	private final String description;
    private final int number;

	RequestState(int number, String description) {
		this.description = description;
		this. number = number;
	}
	public static RequestState fromNumber(int number){
		return Arrays.stream(RequestState.values())
			.filter(state -> state.number == number)
			.findFirst()
			.orElseThrow(()->new CustomException(ErrorCode.BAD_REQUEST_STATE));
	}
}
