package com.fitlog.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.fitlog.dto.QTicketDto;
import com.fitlog.dto.TicketDto;
import com.fitlog.entity.QCenter;
import com.fitlog.entity.QMember;
import com.fitlog.entity.QTicket;
import com.querydsl.jpa.impl.JPAQueryFactory;

//Querydsl로 조회할 메서드를 구현
public class TicketRepositoryCustomImpl implements TicketRepositoryCustom{

	//동적 쿼리를 생성하기 위해 JPAQueryFactory 사용
	public JPAQueryFactory queryFactory;

	//JPAQueryFactory의 생성자로 EntityManager를 초기화함
	public TicketRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	@Override
	public List<TicketDto> findMyTicketDtoList(Long memberNum) {
		QTicket ticket = QTicket.ticket;
		QCenter center = QCenter.center; 
		QMember member = QMember.member;;
		
		return queryFactory
				.select(
						new QTicketDto(
								ticket.ticketNum,
								ticket.ticketName,
								ticket.ticketCount,
								center.centerNum,
								ticket.startDate,
								ticket.endDate,
								member.memberNum,
								member.email)
						)
				.from(ticket)
				.join(ticket.member, member)
				.join(ticket.center, center)
				.where(
						ticket.ticketCount.gt(0),
						member.memberNum.eq(memberNum)
						
						)
				.orderBy(ticket.ticketNum.desc())
				.fetch();
	}
		
	
	


	@Override
	public List<TicketDto> findCenterTicketDtoList(Long centerNum) {
		QTicket ticket = QTicket.ticket;
		QCenter center = QCenter.center; 
		QMember member = QMember.member;
		
		return queryFactory
				.select(
						new QTicketDto(
								ticket.ticketNum,
								ticket.ticketName,
								ticket.ticketCount,
								center.centerNum,
								ticket.startDate,
								ticket.endDate,
								member.memberNum,
								member.email)
						)
				.from(ticket)
				.join(ticket.center, center)
				.join(ticket.member, member)
				.where(
						ticket.ticketCount.gt(0),
						center.centerNum.eq(centerNum)
						
						)
				.orderBy(ticket.ticketNum.desc())
				.fetch();
	}
	
	
	
	
	
}
