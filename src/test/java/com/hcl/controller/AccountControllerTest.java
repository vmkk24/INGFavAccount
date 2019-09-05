package com.hcl.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.dto.RequestEditDto;
import com.hcl.dto.ResponseEditDto;
import com.hcl.entity.FavouriteAccount;
import com.hcl.service.AccountServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
	
	@InjectMocks
	AccountController accountController;
	
	@Mock
	AccountServiceImpl accountServiceImpl;
	@Test
	public void testEditFavouriteAccount() {
		
		RequestEditDto requestEditDto = new RequestEditDto();
		requestEditDto.setAccountName("Mahesh");
		requestEditDto.setFavouriteAccountId(1);
		requestEditDto.setIbanNumber("1234567");
		ResponseEditDto responseEditDto = new ResponseEditDto();
		responseEditDto.setMessage("update Successfully");
		responseEditDto.setStatusCode(200);
		Mockito.when(accountServiceImpl.editFavouriteAccount(requestEditDto)).thenReturn(responseEditDto);
		ResponseEntity<ResponseEditDto> responseEditDto2 = accountController.editFavouriteAccount(requestEditDto);
		assertEquals(responseEditDto.getMessage(), responseEditDto2.getBody().getMessage());
		
	}
	
	
	@Test
	public void testDeleteFavouriteAccount() {
		FavouriteAccount favouriteAccount = new FavouriteAccount();
		favouriteAccount.setFavouriteAccountId(1);
		ResponseEditDto responseEditDto = new ResponseEditDto();
		responseEditDto.setMessage("delete Successfully");
		responseEditDto.setStatusCode(200);
		Mockito.when(accountServiceImpl.deleteFavouriteAccount(Mockito.anyInt())).thenReturn(responseEditDto);
		ResponseEntity<ResponseEditDto> responseEditDto2 = accountController.deleteFavouriteAccount(1);
		assertEquals(responseEditDto.getMessage(), responseEditDto2.getBody().getMessage());
	}

}
