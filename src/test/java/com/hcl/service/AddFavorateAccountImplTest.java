package com.hcl.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.dto.AddAccountInputDto;
import com.hcl.dto.AddAccountOutputDto;
import com.hcl.entity.FavouriteAccount;
import com.hcl.exception.IngBankException;
import com.hcl.repository.FavouriteAccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AddFavorateAccountImplTest {

	@InjectMocks
	AddFavorateAccountImpl addFavorateAccountImpl;
	@Mock
	FavouriteAccountRepository favouriteAccountRepository;

	FavouriteAccount favouriteAccount;
	AddAccountInputDto addAccountInputDto;

	@Before
	public void setup() {
		favouriteAccount = new FavouriteAccount();
		favouriteAccount.setAccountName("sairam");
		favouriteAccount.setCustomerId(1);
		favouriteAccount.setIbanNumber("123123456789234567834567");
		addAccountInputDto = new AddAccountInputDto();
		addAccountInputDto.setCustomerId(1);
		addAccountInputDto.setAccountName(favouriteAccount.getAccountName());
		addAccountInputDto.setIbanNumber(favouriteAccount.getIbanNumber());
	}

	@Test
	public void testAddAccount() {
//		Mockito.when(favouriteAccountRepository.save(favouriteAccount)).thenReturn(favouriteAccount);
		AddAccountOutputDto actual = addFavorateAccountImpl.addAccount(addAccountInputDto);

		Assert.assertEquals(201, actual.getStatusCode().intValue());
	}

	@Test(expected = IngBankException.class)
	public void testNameValidation() {
		addAccountInputDto.setIbanNumber("1@3456");
//		Mockito.when(favouriteAccountRepository.save(favouriteAccount)).thenReturn(favouriteAccount);
		addFavorateAccountImpl.addAccount(addAccountInputDto);

	}

	@Test(expected = IngBankException.class)
	public void testIbanValidation() {
		addAccountInputDto.setIbanNumber("1@34567");
//		Mockito.when(favouriteAccountRepository.save(favouriteAccount)).thenReturn(favouriteAccount);
		addFavorateAccountImpl.addAccount(addAccountInputDto);

	}

}
