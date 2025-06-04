package com.example.feedprep.domain.auth.service;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.auth.dto.AdminSignupRequestDto;
import com.example.feedprep.domain.auth.dto.SignupRequestDto;
import com.example.feedprep.domain.auth.dto.SignupResponseDto;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import com.example.feedprep.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${jwt.secret.key}")
    private String secretCode;

    @Override
    public SignupResponseDto signup(SignupRequestDto requestDto) {

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

        if (!requestDto.getSecretCode().equals(secretCode)) {
            throw new CustomException(ErrorCode.INVALID_SECRET_CODE);
        }

        String userRole = requestDto.getRole();

        // 대소문자 상관없이 사용자가 입력한 값에 따라 처리
        if (userRole.equalsIgnoreCase("ADMIN")) {
            userRole = "ADMIN";
        }  else throw new CustomException(ErrorCode.INVALID_ROLE_REQUEST);

        UserRole finalRole = UserRole.valueOf(userRole);
        return createUser(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword(), finalRole);
    }

    private SignupResponseDto createUser(String name, String email, String password, UserRole role) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(name, email, encodedPassword, role);
        userRepository.save(user);
        return SignupResponseDto.from(user);
    }

}
