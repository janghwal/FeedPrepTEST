package com.example.feedprep.domain.user.repository;

import java.util.List;
import java.util.Map;

import com.example.feedprep.common.exception.base.CustomException;
import com.example.feedprep.domain.user.entity.User;
import com.example.feedprep.domain.user.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//피드백 요청용 조회 문

	@Query("SELECT u FROM User u WHERE u.userId IN(:userId,:tutorId)")
	List<User> findByIds(@Param("userId") Long userId, @Param("tutorId") Long tutorId);

}
