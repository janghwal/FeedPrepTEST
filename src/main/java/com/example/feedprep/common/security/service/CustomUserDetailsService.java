package com.example.feedprep.common.security.service;

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
        User user = userRepository.getUserByNameOrElseThrow(email);
        return new CustomUserDetails(user);
    }
}
