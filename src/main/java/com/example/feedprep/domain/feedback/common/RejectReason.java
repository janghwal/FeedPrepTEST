package com.example.feedprep.domain.feedback.common;

public enum RejectReason {
	INAPPROPRIATE_CONTENT("부적절한 내용"),
	DUPLICATE_FEEDBACK("중복된 피드백"),
	INSUFFICIENT_DETAIL("내용이 너무 부족해서 판단 불가"),
	OUT_OF_SCOPE("서비스 범위를 벗어난 요청 "),
	EXPIRED("일정 기간 초과로 피드백 불가"),
	ETC("기타사유");

	private final String description;

	RejectReason(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
