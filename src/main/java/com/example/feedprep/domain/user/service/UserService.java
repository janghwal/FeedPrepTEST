package com.example.feedprep.domain.user.service;

import com.example.feedprep.domain.user.dto.request.NewPasswordRequestDto;
import com.example.feedprep.domain.user.dto.request.UpdateMyInfoRequestDto;
import com.example.feedprep.domain.user.dto.response.PasswordModifiedAtResponseDto;
import com.example.feedprep.domain.user.dto.response.TutorResponseDto;
import com.example.feedprep.domain.user.dto.response.UserResponseDto;
import com.example.feedprep.domain.user.enums.UserRole;
import java.util.List;

public interface UserService {

    List<TutorResponseDto> getTutorList(UserRole role);

    UserResponseDto getMyInfo(Long tokenMyId);

    UserResponseDto putMyInfo(Long tokenMyId, UpdateMyInfoRequestDto requestDto);

    PasswordModifiedAtResponseDto patchPassword(Long tokenMyId, NewPasswordRequestDto requestDto);
}
