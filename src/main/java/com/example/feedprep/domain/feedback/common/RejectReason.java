package com.example.feedprep.domain.feedback.common;

public enum RejectReason {
	INAPPROPRIATE_CONTENT,   // 부적절한 내용 (비속어, 공격적 표현 등)
	DUPLICATE_FEEDBACK,      // 중복된 피드백 (이미 접수된 내용과 동일)
	INSUFFICIENT_DETAIL,     // 내용이 너무 부족해서 판단 불가
	OUT_OF_SCOPE,           // 서비스 범위를 벗어난 요청 (예: 담당 외 영역)
	EXPIRED                 // 일정 기간 초과로 피드백 불가
}
