package com.example.feedprep.domain.user.repository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //피드백 요청용 조회 문
    Optional<User> getUserByName(String email);

    default User getUserByNameOrElseThrow(String email) {
        return getUserByName(email).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
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
