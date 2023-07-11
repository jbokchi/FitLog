package com.fitlog.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.modelmapper.ModelMapper;

import com.fitlog.entity.Center;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class CenterDto {

	@NotBlank
	private Long centerNum;
	@NotEmpty
	private String centerName;
	@NotEmpty
	private String centerAddr;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public CenterDto(Long centerNum, String centerName, String centerAddr) {
		this.centerNum = centerNum;
		this.centerName = centerName;
		this.centerAddr = centerAddr;
	}
	
	public static CenterDto of(Center Center) {
		return modelMapper.map(Center, CenterDto.class);
	}
	 
}
