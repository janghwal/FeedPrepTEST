package com.example.feedprep.domain.matching.dto;

import java.util.List;

import com.querydsl.core.Tuple;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MatchingDto {
	private Long tutorId;
	private String name;
	private Double rating;
	private String introduction;
	@Setter
	private List<String> techStacks;

	public MatchingDto(Tuple tuple){
		this.tutorId = tuple.get(0, Long.class);
		this.name = tuple.get(1, String.class);
		this.rating = tuple.get(2, Double.class);
		this.introduction = tuple.get(3, String.class);
	}
}
