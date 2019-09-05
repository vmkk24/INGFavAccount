package com.hcl.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.dto.LoginDetailsDto;
import com.hcl.dto.LoginDto;
import com.hcl.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

	@Mock
	LoginService loginService;
	
	@InjectMocks
	LoginController loginController;
	
	LoginDto loginDto = null;
	LoginDetailsDto loginDetailsDto = null;
	
	@Before
	public void setup() {
		loginDto = new LoginDto();
		loginDto.setCustomerId(1);
		
		loginDetailsDto = new LoginDetailsDto();
		loginDetailsDto.setStatusCode(200);
	}
	
	@Test
	public void loginTest() {
		Mockito.when(loginService.login(loginDto)).thenReturn(loginDetailsDto);
		ResponseEntity<LoginDetailsDto> responseEntity = loginController.login(loginDto);
		LoginDetailsDto loDetailsDto = responseEntity.getBody();
		assertEquals(loginDetailsDto.getStatusCode(), loDetailsDto.getStatusCode());
	}
}
