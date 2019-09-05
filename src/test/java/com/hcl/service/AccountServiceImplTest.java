package com.hcl.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Optional;

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
import com.hcl.repository.FavouriteAccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	
	@Mock
	FavouriteAccountRepository favouriteAccountRepository;

	@Test
	public void testSuccessEditFavouriteAccount() {

		RequestEditDto requestEditDto = new RequestEditDto();
		requestEditDto.setAccountName("Mahesh");
		requestEditDto.setFavouriteAccountId(1);
		requestEditDto.setIbanNumber("12345678");
		FavouriteAccount favouriteAccount = new FavouriteAccount();
		favouriteAccount.setAccountName(requestEditDto.getAccountName());
		favouriteAccount.setIbanNumber(requestEditDto.getIbanNumber());

		Mockito.when(favouriteAccountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(favouriteAccount));
		Mockito.when(favouriteAccountRepository.save(favouriteAccount)).thenReturn(favouriteAccount);
		ResponseEditDto responseEditDto = accountServiceImpl.editFavouriteAccount(requestEditDto);
		ResponseEditDto responseEditDto2 = new ResponseEditDto();
		responseEditDto2.setMessage("update Successfully");
		responseEditDto2.setStatusCode(200);
		assertEquals(responseEditDto2.getMessage(), responseEditDto.getMessage());

	}

	@Test
	public void testFailEditFavouriteAccount() {

		RequestEditDto requestEditDto = new RequestEditDto();
		requestEditDto.setAccountName("Mahesh");
		requestEditDto.setFavouriteAccountId(1);
		requestEditDto.setIbanNumber("12345678");
		FavouriteAccount favouriteAccount = new FavouriteAccount();
		ResponseEditDto responseEditDto = accountServiceImpl.editFavouriteAccount(requestEditDto);
		ResponseEditDto responseEditDto2 = new ResponseEditDto();
		responseEditDto2.setMessage("account details not available");
		responseEditDto2.setStatusCode(200);
		assertEquals(responseEditDto2.getMessage(), responseEditDto.getMessage());

	}
	
	@Test
	public void testDeleteFavouriteAccount() {
		FavouriteAccount favouriteAccount = new FavouriteAccount();
		favouriteAccount.setFavouriteAccountId(1);
		ResponseEditDto responseEditDto = new ResponseEditDto();
		responseEditDto.setMessage("delete successfully");
		responseEditDto.setStatusCode(200);
		Mockito.when(favouriteAccountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(favouriteAccount));
		favouriteAccount.setStatus("INACTIVE");
		ResponseEditDto responseEditDto2 = accountServiceImpl.deleteFavouriteAccount(favouriteAccount.getFavouriteAccountId());
		assertEquals(responseEditDto.getMessage(), responseEditDto2.getMessage());
	
	}
}
