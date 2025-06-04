package com.example.feedprep.domain.user.repository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByName(String email);

    default User getUserByNameOrElseThrow(String email) {
        return getUserByName(email).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

}
