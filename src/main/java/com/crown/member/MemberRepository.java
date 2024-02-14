package com.crown.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer>
{
	Member findById(String id);
	Member findBySeq(int seq);
	Member findByName(String name);

}
