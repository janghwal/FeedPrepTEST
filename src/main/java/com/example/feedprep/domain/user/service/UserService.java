package com.example.feedprep.domain.user.service;

import com.example.feedprep.domain.user.dto.request.NewPasswordRequestDto;
import com.example.feedprep.domain.user.dto.request.UpdateMyInfoRequestDto;
import com.example.feedprep.domain.user.dto.response.PasswordModifiedAtResponseDto;
import com.example.feedprep.domain.user.dto.response.TutorResponseDto;
import com.example.feedprep.domain.user.dto.response.UserResponseDto;
import java.util.List;

public interface UserService {

    List<TutorResponseDto> getTutorList();

    UserResponseDto getMyInfo(Long userId);

    UserResponseDto updateMyInfo(Long userId, UpdateMyInfoRequestDto requestDto);

    PasswordModifiedAtResponseDto changePassword(Long userId, NewPasswordRequestDto requestDto);
}
