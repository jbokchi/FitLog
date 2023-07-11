package com.fitlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.fitlog.dto.CenterDto;
import com.fitlog.entity.Center;
import com.fitlog.repository.CenterRepository;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class CenterServiceTest {

	@Autowired
	CenterService centerService;
	@Autowired
	CenterRepository centerRepository;
	
	public Center createCenter() {
		CenterDto centerDto = new CenterDto();
		centerDto.setCenterNum((long) 1);
		centerDto.setCenterName("OO필라테스");
		centerDto.setCenterAddr("울산시 남구");
		
		Center center = new Center(centerDto.getCenterNum(), centerDto.getCenterName(), centerDto.getCenterAddr());
		
		return center;
	}
	
	@Test
	@DisplayName("센터 조회 테스트")
	public void CenterListTest() {
		Center center = createCenter();
		centerRepository.save(center);
		
		List<CenterDto> centerDtoList = centerService.getCenterList();
		
		
		System.out.println("centerDtoList : " + centerDtoList);
		
		assertEquals(centerService.getCenterList().size(), 1);
		assertEquals(centerDtoList.get(0).getCenterName(), "OO필라테스");
	}
	
	
	
}
