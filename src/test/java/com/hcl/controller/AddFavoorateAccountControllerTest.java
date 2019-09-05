package com.hcl.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.dto.AddAccountInputDto;
import com.hcl.dto.AddAccountOutputDto;
import com.hcl.service.AddFavorateAccount;

@RunWith(MockitoJUnitRunner.class)
public class AddFavoorateAccountControllerTest { 

	@InjectMocks
	AddFavoorateAccountController addFavoorateAccountController;
	@Mock
	AddFavorateAccount addFavorateAccount;

	AddAccountInputDto addAccountInputDto;
	AddAccountOutputDto addAccountOutputDto;

	@Before
	public void setup() {
		addAccountInputDto = new AddAccountInputDto();
		addAccountInputDto.setAccountName("sairam");
		addAccountInputDto.setCustomerId(1);
		addAccountInputDto.setIbanNumber("1235678901234567890");

		addAccountOutputDto = new AddAccountOutputDto();
		addAccountOutputDto.setMessage("added succsessfully");
		addAccountOutputDto.setStatusCode(201);
	}

	@Test
	public void addAccount() {
		Mockito.when(addFavorateAccount.addAccount(addAccountInputDto)).thenReturn(addAccountOutputDto);
		ResponseEntity<AddAccountOutputDto> actual = addFavoorateAccountController.addAccount(addAccountInputDto);
		Assert.assertEquals(201, actual.getStatusCodeValue());

	}

}
