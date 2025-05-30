package com.example.feedprep.domain.feedbackreview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;

import org.springframework.stereotype.Repository;
import com.example.feedprep.common.entity.BaseTimeEntity;

@Getter
@Entity
@Table(name = "feedbackreviews")
public class FeedBackReview extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int Rating;
	private  String Content;
}
