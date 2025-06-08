package com.example.feedprep.domain.feedbackrequestentity.common;

public enum RequestState {

	PENDING("요청 대기중"),
	APPROVED("요청 수락 됨"),
	REJECTED ("거절 됨"),
	IN_PROGRESS("피드백 작성 중"),
	COMPLETED("피드백 작성 완료"),
	CANCELED("피드백 요청이 취소됨");
	private final String description;

	RequestState(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
