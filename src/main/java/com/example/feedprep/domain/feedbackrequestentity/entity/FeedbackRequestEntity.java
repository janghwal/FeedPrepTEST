package com.example.feedprep.domain.feedbackrequestentity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;

import com.example.feedprep.common.entity.BaseTimeEntity;
import com.example.feedprep.domain.feedbackrequestentity.common.RequestState;

@Getter
@Entity
@Table(name = "feedbackrequestentitys")
public class FeedbackRequestEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//private Tutor tutor
	//private Document document
	@Column(nullable = false)
	private RequestState requestState;



}
