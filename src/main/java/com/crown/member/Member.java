package com.crown.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.crown.Entity.CommonEntity;
import com.crown.constant.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member extends CommonEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seq; //회원 번호(회원 구분 및 개수 파악용) PK

	@Column(name="member_id", unique=true)
	private String id; //회원 id

	private String name; // 회원 실명

	private String password; // 회원 비밀번호

	private String email; // 회원 이메일

	private String address; // 회원 주소

	@Enumerated(EnumType.STRING) //회원 권한 - ADMIN, USER
	private Role role;

	//회원가입 method
	public static Member createMember(MemberDTO memberFormDTO,
			PasswordEncoder passwordEncoder)
	{
		Member member = new Member();
		member.setId(memberFormDTO.getId());
		member.setName(memberFormDTO.getName());
		member.setEmail(memberFormDTO.getEmail());
		member.setAddress(memberFormDTO.getAddress());
		String paswword = passwordEncoder.encode(memberFormDTO.getPassword());
		member.setPassword(paswword);
		member.setRole(Role.USER);
		return member;
	}
}
