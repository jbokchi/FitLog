package com.fitlog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.constant.Role;
import com.fitlog.dto.MemberDto;
import com.fitlog.entity.Center;
import com.fitlog.entity.Member;
import com.fitlog.entity.Register;
import com.fitlog.repository.CenterRepository;
import com.fitlog.repository.MemberRepository;
import com.fitlog.repository.RegisterRepository;

import lombok.RequiredArgsConstructor;

@Service //서비스 비즈니스 로직을 담당
@Transactional //트랜잭션 처리(에러발생 시 롤백)
@RequiredArgsConstructor //빈을 주입
public class MemberService implements UserDetailsService{

	private final RegisterRepository registerRepository;
	private final CenterRepository centerRepository;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member); //이메일 중복 확인 메서드 호출
		return memberRepository.save(member); //member 저장
	}
	
	//중복된 이메일이 있으면 예외를 발생시킴
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		
		//이미 이메일 주소가 존재하면
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		//User.builder():springSecurity에서 사용자 정보를 생성하는 빌더 패턴을 적용
		//				UserDetails 인터페이스를 구현한 객체를 생성하고 초기화 함
		
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}
	
	//일반회원가입
	public Member createNormalMember(MemberDto memberDto) {
		
		Member member = new Member(memberDto, Role.NORMAL, passwordEncoder);

		return member;
	}
	
	
	//강사회원가입
	public Member createInstructorMember(MemberDto memberDto) {
		
		Member member = new Member(memberDto, Role.INSTRUCTOR, passwordEncoder);
		
		return member;
	}
	
	//레지스터 엔티티 리스트 생성
	public List<Register> createRegister(MemberDto memberDto, Member member) {
		

		List<Register> registerList = new ArrayList<>();
		
		//멤버와 센터 저장
		for(int i=0; i<memberDto.getCenterNum().size(); i++) {
			if(memberDto.getCenterNum().get(i) == null) {
				break;
			}
			
			Center center  = centerRepository.findByCenterNum(memberDto.getCenterNum().get(i));
									
			Register register = new Register(member, center);
			registerList.add(register);
			
		}
		
		return registerList;
	}
	
	//레지스터 저장
	public void saveRegister(List<Register> registerList) {
		
		for(Register register : registerList) {
			registerRepository.save(register);
		}
	}
	
	
}
