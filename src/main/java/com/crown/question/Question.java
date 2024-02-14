package com.crown.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.crown.member.Member;
import com.crown.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(length=200)
	private String title;
	
	@Column(length=20000)
	private String content;
	
	private LocalDateTime regdate;
	private LocalDateTime modifyDate;
	
	@OneToMany(mappedBy = "question", cascade=CascadeType.REMOVE )
	private List<Answer> answerList;
	
	@ManyToOne
	private Member mid;
	
	@ManyToMany
	private Set<Member> voter;
}
