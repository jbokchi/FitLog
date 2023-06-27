package com.fitlog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberDto {
	
	@NotBlank(message="이메일은 필수 입력값입니다.")
	@Email(message="이메일 형식으로 입력하세요.")
	private String email;
	@NotBlank(message="비밀번호는 필수 입력값입니다.")
	@Length(min=8, max=16, message="비밀번호는 8~16글자로 입력하세요.")
	private String password;
	@NotEmpty(message="닉네임은 필수 입력값입니다.")
	private String nicknm;
	private String proimg;
	//private Integer mcnum;
	//private Integer mknum;
}
