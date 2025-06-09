package com.example.feedprep.domain.user.service;

import com.example.feedprep.domain.user.dto.response.TutorResponseDto;

public interface AdminService {

    TutorResponseDto approveTutor(Long tutorId);
}
