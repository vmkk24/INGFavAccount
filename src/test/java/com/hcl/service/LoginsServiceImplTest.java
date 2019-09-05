package com.hcl.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.dto.LoginDetailsDto;
import com.hcl.dto.LoginDto;
import com.hcl.entity.Customer;
import com.hcl.exception.IngBankException;
import com.hcl.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginsServiceImplTest {

	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	LoginsServiceImpl loginsService;
	
	LoginDto loginDto = null;
	LoginDetailsDto loginDetailsDto = null;
	Customer customer = null;
	
	@Before
	public void setup() {
		loginDto = new LoginDto();
		loginDto.setCustomerId(1);
		
		loginDetailsDto = new LoginDetailsDto();
		loginDetailsDto.setStatusCode(200);
		
		customer = new Customer();
		customer.setCustomerId(1);
	}
	
	@Test
	public void loginSuccessTest() {
		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customer));
		LoginDetailsDto responseEntity = loginsService.login(loginDto);
		assertEquals(loginDetailsDto.getStatusCode(), responseEntity.getStatusCode());
	}
	
	@Test(expected = IngBankException.class)
	public void loginFailureTest() {
		loginDto = new LoginDto();
		loginDto.setCustomerId(1);
		
		loginDetailsDto = new LoginDetailsDto();
		loginDetailsDto.setStatusCode(200);
		
		customer = new Customer();
		customer.setCustomerId(1);
		
		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customer));
		loginsService.login(loginDto);
	}
	
}
