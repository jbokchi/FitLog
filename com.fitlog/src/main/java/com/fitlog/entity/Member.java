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
	private Long mnum;
	@Column(name="m_email", unique = true)
	private String memail;
	@Column(name="m_password")
	private String mpassword;
	@Column(name="m_nicknm")
	private String mnicknm;
	@Column(name="m_proimg")
	private String mproimg;
	
	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setMemail(memberDto.getMemail());
		
		String password = passwordEncoder.encode(memberDto.getMpassword());
		member.setMpassword(password);
		
		member.setMnicknm(memberDto.getMnicknm());
		member.setMproimg(memberDto.getMproimg());
		
		return member;
	}
}
