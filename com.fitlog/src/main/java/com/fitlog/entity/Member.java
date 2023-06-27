package com.fitlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fitlog.dto.MemberDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="m_num")
	private Long num;
	@Column(name="m_email", unique = true)
	private String email;
	@Column(name="m_password")
	private String password;
	@Column(name="m_nicknm")
	private String nicknm;
	@Column(name="m_proimg")
	private String proimg;
	
	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setEmail(memberDto.getEmail());
		
		String password = passwordEncoder.encode(memberDto.getPassword());
		member.setPassword(password);
		
		member.setNicknm(memberDto.getNicknm());
		member.setProimg(memberDto.getProimg());
		
		return member;
	}
}
