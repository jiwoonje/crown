package com.crown.member;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService
{

    private final MemberRepository memberRepository;

    //회원정보 등록
    public Member saveMember(Member member)
    {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    
    //회원 1명
    public Member getMember(Member member)
    {
    	return memberRepository.findByName(member.getName());
    }
    
    //회원가입 중복 확인
    private void validateDuplicateMember(Member member)
    {
        Member findMember = memberRepository.findById(member.getId());
        if(findMember != null)
        {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException
    {

        Member member = memberRepository.findById(id);

        //로그로 변수의 값을 출력 : 배포한 서버에서는 colsole 에 값을 출력 할 수 없다.

        log.info("client form :  =====> : " + id);
        log.info("sever DB :=====> : " + member.getId());
        log.info("=====> : " + member.getRole());
        log.info("=====> : " + member.getRole().toString());


        // 넘겨받은 ID (email) 이 DB에 존재하지 않을 경우
        if(member == null)
        {
            throw new UsernameNotFoundException(id);  //예외(오류)를 강제로 발생 시킴 ""
        }


        // User 객체에는 3가지 값이 반드시 적용 : 1. ID , 2. Pass, 3. Authorization (Role)
        // Spring Security 에서 인증이 완료되면  ROLE_USER, ROLE_ADMIN
        return User.builder()
                .username(member.getId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

}