package com.crown.answer;

import java.time.LocalDateTime;

import com.crown.member.Member;
import com.crown.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	@Column(length=20000)
	private String content;
	
	private LocalDateTime regdate;
	private LocalDateTime modifyDate;
	
	@ManyToOne
	private Question qid;
	
	@ManyToOne
	private Member mid;
}
