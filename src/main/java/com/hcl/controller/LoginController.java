package com.hcl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.LoginDetailsDto;
import com.hcl.dto.LoginDto;
import com.hcl.service.LoginService;

/**
 * 
 * @author Lakshmi
 */
@RestController
@CrossOrigin(origins = {"*","*/"},allowedHeaders = {"*","*/"})
public class LoginController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired 
	LoginService loginService;
	
	/**
	 * This method is intended for login of customer
	 * @param loginDto
	 * @return loginDetailsdto
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginDetailsDto> login(@RequestBody LoginDto loginDto){
		LOGGER.debug("LoginController login()");
		LoginDetailsDto loginDetailsDto = loginService.login(loginDto);
		return new ResponseEntity<>(loginDetailsDto,HttpStatus.OK);
	}
}
