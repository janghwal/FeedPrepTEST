// package com.example.feedprep.domain.user.repository;
//
// import java.util.List;
//
// import lombok.RequiredArgsConstructor;
//
// import com.example.feedprep.domain.user.entity.QUser;
// import com.example.feedprep.domain.user.entity.User;
// import com.example.feedprep.domain.user.enums.UserRole;
// import com.querydsl.core.types.dsl.BooleanExpression;
// import com.querydsl.jpa.impl.JPAQueryFactory;
//
// @RequiredArgsConstructor
// public class UserRepositoryCustomImpl implements UserRepositoryCustom {
// 	private final JPAQueryFactory jpaQueryFactory;
// 	private final QUser user = QUser.user;
//
// 	@Override
// 	public List<User> findByUsers(Long userId, UserRole userRole, Long tutorId, UserRole tutorRole) {
//
// 		return jpaQueryFactory
// 			.selectFrom(user)
// 			.where( user.userId.eq(userId).and(user.role.eq(userRole)).or(
// 					user.userId.eq(tutorId).and(user.role.eq(tutorRole))))
// 			.limit(2)
// 			.fetch();
// 	}
// }
