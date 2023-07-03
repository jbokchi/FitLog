package com.fitlog.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class SchedulerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("내 스케줄 권한 테스트")
	@WithMockUser(username="그린", roles="NORMAL")
	public void schduleFormTest() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/scheduler/tickets"))
			.andDo(print()) //요청과 응답메시지를 콘솔에 출력
			.andExpect(status().isOk()); //응답상태가 OK인지 확인
	
	}
	
	@Test
	@DisplayName("상품 등록 강사 회원 접근 테스트")
	@WithMockUser(username="강사", roles="INSTRUCTOR")
	public void scheduleFormNotNormalTest() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/scheduler/tickets"))
			.andDo(print())
			.andExpect(status().isForbidden()); //예외가 발생하면 통과
		
	}
	
}
