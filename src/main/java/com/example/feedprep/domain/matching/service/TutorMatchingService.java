package com.example.feedprep.domain.matching.service;

import java.util.List;

import com.example.feedprep.domain.matching.dto.MatchingDto;

public interface TutorMatchingService {
	List<MatchingDto> tutorMatching(Long studentId, int page);
}
