package com.example.feedprep.domain.user.repository;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.common.exception.enums.ErrorCode;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(UserRole role);

    default User findByIdOrElseThorw(Long id) {
        User user = findById(id).orElseThrow(
            () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        return user;
    }
}
