package com.example.feedprep.domain.feedbackrequestentity.common;

import java.util.Arrays;

import lombok.Getter;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;

@Getter
public enum RejectReason {
	INAPPROPRIATE_CONTENT(0,"부적절한 내용"),
	DUPLICATE_FEEDBACK(1,"중복된 피드백"),
	INSUFFICIENT_DETAIL(2,"내용이 너무 부족해서 판단 불가"),
	OUT_OF_SCOPE(3,"서비스 범위를 벗어난 요청 "),
	EXPIRED(4,"일정 기간 초과로 피드백 불가"),
	ETC(5,"기타사유");

	private final String description;
	private final int reasonNumber;
	RejectReason(int reasonNumber, String description)
	{
		this.reasonNumber= reasonNumber;
		this.description = description;
	}

	public static RejectReason fromNumber(int number){
		return Arrays.stream(RejectReason.values())
			.filter(reason-> reason.reasonNumber == number)
			.findFirst()
			.orElseThrow(()->new CustomException(ErrorCode.BAD_REQUEST_STATE));
	}
}
