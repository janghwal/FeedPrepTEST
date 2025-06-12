package com.example.feedprep.domain.user.service;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.user.dto.response.TutorResponseDto;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import com.example.feedprep.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public TutorResponseDto approveTutor(Long tutorId) {

        User user = userRepository.findByIdOrElseThrow(tutorId);

        if(!user.getRole().equals(UserRole.PENDING_TUTOR)){
            throw new CustomException(ErrorCode.NOT_PENDING_TUTOR);
        }

        user.setRole(UserRole.APPROVED_TUTOR);

        return new TutorResponseDto(user.getRole());
    }
}
