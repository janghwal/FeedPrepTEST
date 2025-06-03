package com.example.feedprep.domain.feedbackrequestentity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;

@Repository
public interface FeedbackRequestEntityRepository extends JpaRepository<FeedbackRequestEntity, Long>
	, FeedbackRequestEntityRepositoryCustom{

	@Query("SELECT f FROM FeedbackRequestEntity f "
		+ "WHERE f.user.userId = :userId "
		+ "AND f.tutor.userId = :tutorId "
		+ "AND f.requestState = :state"
	)
	Optional<FeedbackRequestEntity> findTop1ByUser_UserIdAndTutor_UserIdAndContentAndRequestState(
		@Param("userId")Long userId
		,@Param("tutorId")Long tutorId
		,@Param("state") RequestState state);
}
