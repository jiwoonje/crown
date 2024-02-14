package com.crown.member;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO
{
	@NotBlank(message = "필수 입력 사항입니다.")
	private String id;

	@NotBlank(message = "필수 입력 사항입니다.")
	private String name;

	@NotEmpty(message = "필수 입력 사항입니다.")
	@Email(message = "정확한 형식으로 입력해주세요.")
	private String email;

	@NotEmpty(message = "필수 입력 사항입니다.")
	@Length(min = 8, max = 16, message = "8자이상 16자 이하로 입력하세요.")
	private String password;

	@NotEmpty(message = "필수 입력 사항입니다.")
	private String address;
}
