package com.example.feedprep.domain.feedbackrequestentity.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;

public interface FeedbackRequestEntityRepositoryCustom {

	Page<FeedbackRequestEntity> findByRequest(
		Long userId,
		Long tutorId,
		Long documentId,
		LocalDateTime month,
		PageRequest pageRequest);
}
