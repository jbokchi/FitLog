package com.fitlog.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fitlog.entity.Center;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
	@Length(min=2, max=8, message="닉네임은 2~8글자로 입력하세요.")
	private String nicknm;
	private String proimg;
	
	private List<Long> centerNum;

	public MemberDto() {};
	
	@Builder
	public MemberDto(String email, String password, String nicknm, String proimg, List<Long> centerNum) {
		this.email = email;
		this.password = password;
		this.nicknm = nicknm;
		this.proimg = proimg;
		this.centerNum = centerNum;
	}
	
	
	
	
}
