package com.fitlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitlog.dto.CenterDto;
import com.fitlog.entity.Center;

public interface CenterRepository extends JpaRepository<Center, Long>{

	List<Center> findAll();
	
	Center findByCenterNum(Long centerNum);
}
