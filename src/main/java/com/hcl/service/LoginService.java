package com.hcl.service;

import com.hcl.dto.LoginDetailsDto;
import com.hcl.dto.LoginDto;

public interface LoginService {
	public LoginDetailsDto login(LoginDto loginDto);
}
