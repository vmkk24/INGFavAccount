package com.hcl.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dto.LoginDetailsDto;
import com.hcl.dto.LoginDto;
import com.hcl.entity.Customer;
import com.hcl.exception.IngBankException;
import com.hcl.repository.CustomerRepository;
import com.hcl.util.IngConstants;

/**
 * 
 * @author Lakshmi
 */
@Service
public class LoginsServiceImpl implements LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginsServiceImpl.class);
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public LoginDetailsDto login(LoginDto loginDto) {
		LOGGER.debug("LoginsServiceImpl login");
		LoginDetailsDto loginDetailsDto = null;
		Optional<Customer> customer = customerRepository.findById(loginDto.getCustomerId());
		if (!customer.isPresent()) {
			throw new IngBankException(IngConstants.LOGIN_FAILURE);
		} else {
			loginDetailsDto = new LoginDetailsDto();
			loginDetailsDto.setStatusCode(200);
			loginDetailsDto.setName(customer.get().getName());
			loginDetailsDto.setMessage(IngConstants.LOGIN_SUCCESS);
			return loginDetailsDto;
		}
	}
}
