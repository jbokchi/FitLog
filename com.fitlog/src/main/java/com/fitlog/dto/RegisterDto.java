package com.fitlog.dto;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.fitlog.entity.Center;
import com.querydsl.core.annotations.QueryProjection;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class RegisterDto {

	@NotBlank
	private Long memberNum;
	@NotBlank
	private Long centerNum;

	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static CenterDto of(Center Center) {
		return modelMapper.map(Center, CenterDto.class);
	}

	@Builder
	@QueryProjection
	public RegisterDto(Long memberNum, Long centerNum) {
		
		this.memberNum = memberNum;
		this.centerNum = centerNum;
	}
	
	
}
