package com.example.feedprep.common.security.service;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.common.security.jwt.CustomUserDetails;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.getUserByEmailOrElseThrow(email);

        if (user.getDeletedAt() != null) {
            throw new CustomException(ErrorCode.WITHDRAWN_USER);
        }

        return new CustomUserDetails(user);
    }
}
