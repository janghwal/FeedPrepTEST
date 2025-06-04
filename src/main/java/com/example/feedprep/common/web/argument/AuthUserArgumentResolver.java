package com.example.feedprep.common.web.argument;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.common.security.jwt.CustomUserDetails;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override

    public boolean supportsParameter(MethodParameter parameter) {
        // 어노테이션 : AuthUser , Long 타입(ex : Long userId)일때만 호출
        return parameter.getParameterAnnotation(AuthUser.class) != null
                && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory
    ) throws CustomException {
        // 1. 인증 객체 꺼내옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 2. 인증 객체가 비어 있거나  CustomUserDetails 타입이 아닐 경우
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
            if (authUser == null || authUser.required()) {
                // 유효 하지 않은 토큰 예외 처리
                throw new CustomException(ErrorCode.MISSING_TOKEN);
            }
            return null;
        }
        // 유효할 경우 userId 반환
        return userDetails.getUser().getUserId();
    }
}
