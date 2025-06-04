package com.example.feedprep.domain.user.repository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import com.example.feedprep.domain.user.enums.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByName(String email);

    default User getUserByNameOrElseThrow(String email) {
        return getUserByName(email).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

	//피드백 요청용 조회 문

	@Query("SELECT u FROM User u WHERE u.userId IN(:userId,:tutorId)")
	List<User> findByIds(@Param("userId") Long userId, @Param("tutorId") Long tutorId);

    List<User> findAllByRole(UserRole role);

    default User findByIdOrElseThrow(Long id) {
        User user = findById(id).orElseThrow(
            () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        return user;
    }
}
