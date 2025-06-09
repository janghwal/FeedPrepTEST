package com.example.feedprep.domain.user.controller;

import static com.example.feedprep.common.exception.enums.SuccessCode.*;

import com.example.feedprep.common.response.ApiResponseDto;
import com.example.feedprep.domain.user.dto.response.TutorResponseDto;
import com.example.feedprep.domain.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminContoller {

    private AdminService adminService;

    @PostMapping("/tutor")
    public ResponseEntity<ApiResponseDto<TutorResponseDto>> approveTutor(
        @PathVariable Long tutorId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDto.success(APPROVE_TUTOR,adminService.approveTutor(tutorId)));
    }
}
