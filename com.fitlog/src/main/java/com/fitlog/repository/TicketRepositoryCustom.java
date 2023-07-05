package com.fitlog.repository;

import java.util.List;

import com.fitlog.dto.TicketDto;

//Querydsl을 사용하여 Jpa 엔티티와 관련된 사용자 정의 인터페이스
//Querydsl의 쿼리 타입으로 사용함
public interface TicketRepositoryCustom {

	List<TicketDto> findTicketDtoList(Long memberNum);
}
