package com.example.feedprep.domain.feedbackrequestentity.repository;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;
import com.example.feedprep.domain.feedbackrequestentity.entity.FeedbackRequestEntity;
import com.example.feedprep.domain.feedbackrequestentity.entity.QFeedbackRequestEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@RequiredArgsConstructor
public class FeedbackRequestEntityRepositoryImpl implements FeedbackRequestEntityRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	private final QFeedbackRequestEntity feedbackRequestEntity = QFeedbackRequestEntity.feedbackRequestEntity;

	@Override
	public Page<FeedbackRequestEntity> findByRequest(
		Long userId,
		Long tutorId,
		Long documentId,
		LocalDateTime month,
		PageRequest pageRequest) {
		//필수 고정
		BooleanExpression fixed = feedbackRequestEntity .user.userId.eq(userId)
			.and(feedbackRequestEntity.requestState.eq(RequestState.PENDING));

		BooleanBuilder dynamic= new BooleanBuilder();
		if (tutorId != null) {
			dynamic.and(feedbackRequestEntity .tutor.userId.eq(tutorId));
		}
		if (documentId != null) {
			dynamic.and(feedbackRequestEntity.document.documentId.eq(documentId));
		}
		if (month != null) {
			YearMonth ym = YearMonth.from(month);
			LocalDateTime start = ym.atDay(1).atStartOfDay();
			LocalDateTime end = ym.atEndOfMonth().atTime(23, 59, 59);
			dynamic.and(feedbackRequestEntity.createdAt.between(start, end));
		}
		List<FeedbackRequestEntity> content = queryFactory
			.selectFrom(feedbackRequestEntity)
			.where(fixed, dynamic)
			.offset(pageRequest.getOffset())
			.limit(pageRequest.getPageSize())
			.orderBy(feedbackRequestEntity.createdAt.desc())
			.fetch();

		Long total = queryFactory
			.select(feedbackRequestEntity.count())
			.from(feedbackRequestEntity)
			.where(fixed, dynamic)
			.fetchOne();

		return new PageImpl<>(content, pageRequest, total != null ? total : 0L);
	}
}
