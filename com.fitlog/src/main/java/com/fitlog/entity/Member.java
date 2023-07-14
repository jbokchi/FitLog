package com.fitlog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fitlog.constant.Role;
import com.fitlog.dto.MemberDto;

import lombok.Builder;
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
	private Long memberNum;
	@Column(name="m_email", unique = true)
	private String email;
	@Column(name="m_password")
	private String password;
	@Column(name="m_nicknm")
	private String nicknm;
	@Column(name="m_proimg")
	private String proimg;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	@OneToMany(mappedBy = "member")
	private List<Ticket> tickets = new ArrayList<>();
	
	@OneToMany(mappedBy = "member")
	private List<Register> registerList = new ArrayList<>();
	
	public Member() {};
	
	//1. 멤버생성
	@Builder
	public Member(MemberDto memberDto, Role role,PasswordEncoder passwordEncoder) {
		this.email = memberDto.getEmail();
		this.password = passwordEncoder.encode(memberDto.getPassword());
		this.nicknm = memberDto.getNicknm();
		this.role = role;
	}

	
	
		
}
