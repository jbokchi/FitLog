package com.fitlog.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.dto.CenterDto;
import com.fitlog.entity.Center;
import com.fitlog.repository.CenterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CenterService {

	private final CenterRepository centerRepository;
	
	public List<CenterDto> getCenterList(){
		
		List<Center> centerList = centerRepository.findAll();
		List<CenterDto> centerDtoList = new ArrayList<>();
		
		
		if(centerList.isEmpty()) {
			return centerDtoList;
		}
		
		for(Center center: centerList) {
			CenterDto centerDto = CenterDto.of(center);
			centerDtoList.add(centerDto);
		}
		
		return centerDtoList;
	}
	
	public CenterDto getCenterByCenterNum(Long centerNum) {
		
		Center center = centerRepository.findByCenterNum(centerNum);
		CenterDto centerDto = CenterDto.of(center);
		
		return centerDto;
	}
}
