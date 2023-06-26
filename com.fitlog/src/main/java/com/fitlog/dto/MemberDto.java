package com.fitlog.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberDto {
	
	private String memail;
	private String mpassword;
	private String mnicknm;
	private String mproimg;
	//private Integer mcnum;
	//private Integer mknum;
}
