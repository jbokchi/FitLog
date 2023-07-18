package com.fitlog.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class TicketDto {
	
	@NotBlank
	private Long ticketNum;
	@NotEmpty
	private String ticketName;
	@NotBlank
	private Integer ticketCount;
	@NotBlank
	private Long centerNum;
	@NotBlank	
	private Date startDate;
	@NotBlank
	private Date endDate;
	@NotBlank
	private Long memberNum;
	@NotBlank
	private String memberEmail;
	
	@Builder
	@QueryProjection
	public TicketDto(Long ticketNum, String ticketName, Integer ticketCount, Long centerNum,
			Date startDate, Date endDate, Long memberNum, String memberEmail) {
		this.ticketNum = ticketNum;
		this.ticketName = ticketName;
		this.ticketCount = ticketCount;
		this.centerNum = centerNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.memberNum = memberNum;
		this.memberEmail = memberEmail;
	}
	
}
