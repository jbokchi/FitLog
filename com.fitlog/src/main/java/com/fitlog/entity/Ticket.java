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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fitlog.dto.TicketDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tk_num")
	private Long ticketNum;
	
//	@Column(name="tk_cnum")
//	private Long centerNum;

	@ManyToOne
	@JoinColumn(name="m_num")
	private Member member;
	
	@Column(name="tk_name")
	private String ticketName;
	@Column(name="tk_count")
	private Integer ticketCount;
	@Column(name="tk_period")
	private Integer ticketPeriod;
	
	@Column(name="tk_startDateAt")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Column(name="tk_endDateAt")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	public Ticket(TicketDto ticket, Member member) {
		this.ticketName = ticket.getTicketName();
		this.ticketCount = ticket.getTicketCount();
		this.ticketPeriod = ticket.getTicketPeriod();
		this.member = member;
	}
	
	public Ticket(TicketDto ticket) {
		this.ticketName = ticket.getTicketName();
		this.ticketCount = ticket.getTicketCount();
		this.ticketPeriod = ticket.getTicketPeriod();
	}

	
}	
	

