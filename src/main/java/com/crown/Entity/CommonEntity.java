package com.crown.Entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;


@EntityListeners(value= {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public abstract class CommonEntity extends CommonTimeEntity
{
	//생성한 사람
	@CreatedBy
	@Column(updatable=false)
	private String createdBy;

	//수정한 사람
	@LastModifiedBy
	private String modifiedBy;
}
