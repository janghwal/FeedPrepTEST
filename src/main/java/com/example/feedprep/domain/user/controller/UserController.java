package com.example.feedprep.domain.user.controller;


import static com.example.feedprep.common.exception.enums.SuccessCode.*;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.user.dto.request.NewPasswordRequestDto;
import com.example.feedprep.domain.user.dto.request.UpdateMyInfoRequestDto;
import com.example.feedprep.domain.user.dto.response.PasswordModifiedAtResponseDto;
import com.example.feedprep.domain.user.dto.response.TutorResponseDto;
import com.example.feedprep.domain.user.dto.response.UserResponseDto;
import com.example.feedprep.domain.user.enums.UserRole;
import com.example.feedprep.domain.user.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenInfo tokenInfo;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<TutorResponseDto>>> getTutorList(
        @RequestParam(name = "role") String role
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(GETTUTORLIST_SUCCESS,userService.getTutorList(UserRole.of(role))
                .stream().toList()));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> getMyInfo() {

        Long tokenMyId = tokenInfo.getTokenInfo(authheader);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(GETMYINFO_SUCCESS,userService.getMyInfo(tokenMyId)));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updateMyInfo(
        @RequestBody UpdateMyInfoRequestDto requestDto
    ) {

        Long tokenMyId = tokenInfo.getTokenInfo(authheader);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(PUTMYINFO_SUCCESS,userService.updateMyInfo(tokenMyId,requestDto)));
    }

    @PatchMapping("/password-update")
    public ResponseEntity<ApiResponseDto<PasswordModifiedAtResponseDto>> changePassword(
        @Valid @RequestBody NewPasswordRequestDto requestDto) {

        Long tokenMyId = tokenInfo.getTokenInfo(authheader);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(PATCHPASSWORD_SUCCESS,userService.changePassword(tokenMyId,requestDto)));
    }
}
