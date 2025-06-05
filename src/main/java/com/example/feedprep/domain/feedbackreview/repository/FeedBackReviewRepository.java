package com.example.feedprep.domain.feedbackreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.feedprep.domain.feedbackreview.entity.FeedbackReview;

@Repository
public interface FeedBackReviewRepository extends JpaRepository<FeedbackReview, Long> {
}
