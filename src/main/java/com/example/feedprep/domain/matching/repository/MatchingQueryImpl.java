package com.example.feedprep.domain.matching.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.feedprep.domain.feedback.entity.QFeedback;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.entity.QFeedbackRequestEntity;
import com.example.feedprep.domain.feedbackreview.entity.QFeedbackReview;
import com.example.feedprep.domain.techstack.entity.QUserTechStack;
import com.example.feedprep.domain.user.entity.QUser;
import com.example.feedprep.domain.user.enums.UserRole;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MatchingQueryImpl implements MatchingQuery{

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Tuple> getTutor(Long userId, int page) {

		QFeedbackRequestEntity feedbackRequest = QFeedbackRequestEntity.feedbackRequestEntity;
		QUser user = QUser.user;
		QFeedback feedback = QFeedback.feedback;
		QFeedbackReview review = QFeedbackReview.feedbackReview;
		QUserTechStack userTechStack = QUserTechStack.userTechStack;


		NumberExpression<Long> pendingOrApprovedCount = new CaseBuilder()
			.when(feedbackRequest.requestState.eq(RequestState.PENDING)
				.or(feedbackRequest.requestState.eq(RequestState.APPROVED)))
			.then(1L)
			.otherwise(0L)
			.sum();

		return jpaQueryFactory
			.select(
				user.userId.as("tutorId"),
				user.name.as("name"),
				review.rating.avg().as("rating"),
				user.introduction.as("introduction")
			)
			.from(user)
			.leftJoin(feedbackRequest).on(user.eq(feedbackRequest.tutor))
			.leftJoin(feedback).on(feedbackRequest.eq(feedback.feedbackRequestEntity))
			.leftJoin(review).on(user.userId.eq(review.tutorId))
			.where(
				user.userId.in(
						JPAExpressions
							.select(userTechStack.user.userId)
							.from(userTechStack)
							.where(
								userTechStack.techStack.techId.in(
									JPAExpressions
										.select(userTechStack.techStack.techId)
										.from(userTechStack)
										.where(userTechStack.user.userId.eq(userId))
								)
							)
					)
					.and(user.role.eq(UserRole.APPROVED_TUTOR))
			)
			.groupBy(user.userId)
			.orderBy(
				pendingOrApprovedCount.asc(),
				review.rating.avg().desc(),
				Expressions.numberTemplate(Long.class, "TIMESTAMPDIFF(SECOND, {0}, {1})", feedback.createdAt, feedbackRequest.createdAt).avg().asc()
			)
			.offset((page-1)* 4L)
			.limit(4)
			.fetch();
	}
}
