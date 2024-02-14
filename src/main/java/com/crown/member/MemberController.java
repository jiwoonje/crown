package com.crown.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController
{
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	//회원 등록 형식
	@GetMapping(value="/new")
	public String memberForm(Model model)
	{
		model.addAttribute("memberFormDTO", model);
		return "member/memberForm";
	}

	//새 회원 등록
	@PostMapping(value="/new")
	public String newMember(@Valid MemberDTO memberFormDTO, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
		{
			return "member/memberForm";
		}

		try
		{
			Member member = Member.createMember(memberFormDTO, passwordEncoder);
			memberService.saveMember(member);
		}

		catch(IllegalStateException e)
		{
			model.addAttribute("오류메시지", e.getMessage());
		}

		return "member/memberForm";
	}

	@GetMapping(value="/login")
	public String loginMember()
	{
		return "/member/memberLogin";
	}

	@GetMapping(value="/login/error")
	public String loginError(Model model)
	{
		model.addAttribute("LoginErrorMsg", "아이디 또는 비밀번호를 다시 확인하세요.");
		return "/member/memberLoginForm";
	}

}
