package com.fitlog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.entity.Member;
import com.fitlog.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service //서비스 비즈니스 로직을 담당
@Transactional //트랜잭션 처리(에러발생 시 롤백)
@RequiredArgsConstructor //빈을 주입
public class MemberService {

	private final MemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member); //이메일 중복 확인 메서드 호출
		return memberRepository.save(member); //member 저장
	}
	
	//중복된 이메일이 있으면 예외를 발생시킴
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByMemail(member.getMemail());
		
		//이미 이메일 주소가 존재하면
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
}
