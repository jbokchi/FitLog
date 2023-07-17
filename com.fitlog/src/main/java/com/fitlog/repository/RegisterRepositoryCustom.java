package com.fitlog.repository;

import java.util.List;

import com.fitlog.dto.RegisterDto;

public interface RegisterRepositoryCustom {

	List<RegisterDto> findRegisterDtoList(Long memberNum);
}
