package com.example.feedprep.domain.auth.service;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.exception.enums.SuccessCode;
import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.common.security.jwt.JwtUtil;
import com.example.feedprep.common.security.jwt.TokenBlacklistService;
import com.example.feedprep.domain.auth.dto.*;
import com.example.feedprep.domain.auth.entity.RefreshToken;
import com.example.feedprep.domain.auth.repository.RefreshTokenRepository;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import com.example.feedprep.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenBlacklistService tokenBlacklistService;

    @Value("${jwt.secret.key}")
    private String secretCode;

    @Override
    public SignupResponseDto signup(SignupRequestDto requestDto) {

        Optional<User> user = userRepository.findByEmail(requestDto.getEmail());
        if (user.isPresent()) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        String userRole = requestDto.getRole();

        // 대소문자 상관없이 사용자가 입력한 값에 따라 처리
        if (userRole.equalsIgnoreCase("TUTOR")) {
            userRole = "PENDING_TUTOR";
        } else if (userRole.equalsIgnoreCase("STUDENT")) {
            userRole = "STUDENT";
        } else throw new CustomException(ErrorCode.INVALID_ROLE_REQUEST);

        UserRole finalRole = UserRole.valueOf(userRole);
        return createUser(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword(), finalRole);
    }

    @Override
    public SignupResponseDto adminSignup(AdminSignupRequestDto requestDto) {
        Optional<User> user = userRepository.findByEmail(requestDto.getEmail());
        if (user.isPresent()) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        if (!requestDto.getSecretCode().equals(secretCode)) {
            throw new CustomException(ErrorCode.INVALID_SECRET_CODE);
        }

        String userRole = requestDto.getRole();

        // 대소문자 상관없이 사용자가 입력한 값에 따라 처리
        if (userRole.equalsIgnoreCase("ADMIN")) {
            userRole = "ADMIN";
        } else throw new CustomException(ErrorCode.INVALID_ROLE_REQUEST);

        UserRole finalRole = UserRole.valueOf(userRole);
        return createUser(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword(), finalRole);
    }



    @Override
    public TokenResponseDto login(LoginRequestDto requestDto, Set<String> allowedRoles) {

        User user = userRepository.getUserByEmailOrElseThrow(requestDto.getEmail());

        RefreshToken existingRefreshToken = refreshTokenRepository.findByUser_UserId(user.getUserId());

        // 특정 유저의 토큰이 이미 존재할 경우 서버에서 삭제
        if (existingRefreshToken != null) {
            refreshTokenRepository.delete(existingRefreshToken);
        }

        if (!allowedRoles.contains(user.getRole().name())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_ROLE_LOGIN);
        }

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        String accessToken = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        String refreshTokenString = jwtUtil.generateRefreshToken(user.getEmail());
        RefreshToken refreshToken = new RefreshToken(refreshTokenString, user);

        refreshTokenRepository.save(refreshToken);

        return new TokenResponseDto(accessToken, refreshTokenString);

    }

    @Override
    public void logout(String authHeader) {
        // Bearer 제거
        String accessToken = jwtUtil.substringToken(authHeader);

        // Claims 추출 (유효성 검증)
        Claims claims = jwtUtil.validateToken(accessToken);
        if (claims == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        // 만료까지 남은 시간 계산 -> 해당 토큰 블랙 리스트 등록
        long remainingMillis = jwtUtil.getRemainingMillis(claims);

        if (remainingMillis > 0) {
           tokenBlacklistService.addTokenToBlacklist(accessToken, remainingMillis);
        }

    }


    private SignupResponseDto createUser(String name, String email, String password, UserRole role) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(name, email, encodedPassword, role);
        userRepository.save(user);
        return SignupResponseDto.from(user);
    }


}
