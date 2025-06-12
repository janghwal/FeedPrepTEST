package com.example.feedprep.domain.user.controller;


import static com.example.feedprep.common.exception.enums.SuccessCode.*;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.common.security.annotation.AuthUser;
import com.example.feedprep.domain.user.dto.request.NewPasswordRequestDto;
import com.example.feedprep.domain.user.dto.request.UpdateMyInfoRequestDto;
import com.example.feedprep.domain.user.dto.response.PasswordModifiedAtResponseDto;
import com.example.feedprep.domain.user.dto.response.TutorResponseDto;
import com.example.feedprep.domain.user.dto.response.UserResponseDto;
import com.example.feedprep.domain.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 검색 인증인가 필요?
    @GetMapping("/tutorList")
    public ResponseEntity<ApiResponseDto<List<TutorResponseDto>>> getTutorList() {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(GET_TUTORLIST_SUCCESS,userService.getTutorList()
                .stream().toList()));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> getMyInfo(
        @AuthUser Long userId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(GET_MYINFO_SUCCESS,userService.getMyInfo(userId)));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updateMyInfo(
        @AuthUser Long userId,
        @RequestBody UpdateMyInfoRequestDto requestDto
    ) {

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(UPDATE_MYINFO_SUCCESS,userService.updateMyInfo(userId,requestDto)));
    }

    @PatchMapping("/me")
    public ResponseEntity<ApiResponseDto<PasswordModifiedAtResponseDto>> changePassword(
        @AuthUser Long userId,
        @Valid @RequestBody NewPasswordRequestDto requestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(CHANGE_PASSWORD_SUCCESS,userService.changePassword(userId,requestDto)));
    }
}
