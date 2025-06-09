package com.example.feedprep.domain.user.repository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //피드백 요청용 조회 문
    Optional<User> getUserByEmail(String email);

    default User getUserByEmailOrElseThrow(String email) {
        return getUserByEmail(email).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @EntityGraph(attributePaths = "documents")
    List<User> findAllByRole(UserRole role);

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
    }

    //피드백 요청용 조회 문
    default User findByIdOrElseThrow(Long id, ErrorCode errorCode) {
        return findById(id).orElseThrow(
            () -> new CustomException(errorCode)
        );
    }

    Optional<User> findByEmail(String email);
}
