package com.example.feedprep.common.constants;

public class BusinessRuleConstants {
    // 액세스 토큰 만료 시간 : 1시간
    public static final long ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;

    // 리프레쉬 토큰 만료 시간 : 일주일
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7;

    // 블랙리스트 접두사
    public static final String BLACKLIST_PREFIX = "blacklist:";

    // 토큰 접두사
    public static final String BEARER_PREFIX = "Bearer ";
}
