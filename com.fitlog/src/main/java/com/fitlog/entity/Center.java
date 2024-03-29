package com.fitlog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="center")
@Getter @Setter
@ToString
public class Center {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="c_num")
	private Long centerNum;
	@Column(name="c_name")
	private String centerName;
	@Column(name="c_addr")
	private String centerAddr;
			
	@OneToMany(mappedBy="center")
	List<Ticket> ticketList = new ArrayList<>();
	
	public Center(){};
	
	@Builder
	public Center(Long centerNum, String centerName, String centerAddr) {
		this.centerNum = centerNum;
		this.centerName = centerName;
		this.centerAddr = centerAddr;
	}
	
	

}
