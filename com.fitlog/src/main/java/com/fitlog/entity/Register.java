package com.fitlog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="registerCenter")
@NoArgsConstructor
@Getter @Setter
@ToString
public class Register {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="r_num")
	private Long registerNum;
	
	@ManyToOne
	@JoinColumn(name="m_num")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="c_num")
	private Center center;
		
	public Register(Member member, Center center) {
		this.member = member;
		this.center = center;
	}

	
	
	
}
