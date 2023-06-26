package com.fitlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.dto.MemberDto;
import com.fitlog.entity.Member;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {

	
	@Autowired
	MemberService memberService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//회원정보를 담은 Member 엔티티를 만드는 메서드 작성
	public Member createMember() {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemail("green@naver.com");
		memberDto.setMpassword("1111");
		memberDto.setMnicknm("김그린");
		
		//엔티티에 회원정보를 저장하고 반환
		return Member.createMember(memberDto, passwordEncoder);
	}
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveMemberTest() {
		Member member = createMember(); //회원정보가 저장된 member 객체(entity)를 생성
		Member savedMember = memberService.saveMember(member); //DB에 저장(저장된 entity를 반환)
		
		//회원정보를 저장하기 위해 요청한 값과 실제 저장된 값을 비교
					//요청값(기댓값)		  //실제 저장된 값
		assertEquals(member.getMemail(), savedMember.getMemail());
		assertEquals(member.getMpassword(), savedMember.getMpassword());
		assertEquals(member.getMnicknm(), savedMember.getMnicknm());
	}
	
	@Test
	@DisplayName("중복회원 가입 테스트")
	public void saveDuplicateMemberTest() {
		Member member1 = createMember();
		Member member2 = createMember();
		memberService.saveMember(member1);
		
		try {
			//중복된 회원이면 IllegalStateException 발생 
			//MemberService에서 validateDuplicateMember()에서
			//중복된 이메일이 존재하면 IllegalStateException 예외를 발생시키고,
			//"이미 가입된 회원입니다." 라는 메세지를 출력하기 때문
			memberService.saveMember(member2);
		
		} catch(IllegalStateException e) {
			//e가 IllegalStateException의 맞으면 true
			assertTrue(e instanceof IllegalStateException);
			//예외 처리 메세지가 같으면 true
			assertEquals("이미 가입된 회원입니다.", e.getMessage());
		}
	}
}
