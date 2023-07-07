package com.fitlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitlog.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByEmail(String Email);
	
	Member findByMemberNum(String MemberNum);
}