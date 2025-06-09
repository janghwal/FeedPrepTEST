package com.example.feedprep.domain.matching.repository;

import java.util.List;

import com.querydsl.core.Tuple;

public interface MatchingQuery {
	List<Tuple> getTutor(Long userId, int page);
}
