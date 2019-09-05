package com.hcl.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.hcl.dto.AddAccountInputDto;
import com.hcl.dto.RestTempleteDto;
import com.hcl.entity.FavouriteAccount;
import com.hcl.exception.IngBankException;
import com.hcl.repository.FavouriteAccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AddFavorateAccountImplTest {

	@InjectMocks
	AddFavorateAccountImpl addFavorateAccountImpl;
	@Mock
	FavouriteAccountRepository favouriteAccountRepository;

	@Mock
	RestTemplate restTemplate;

	FavouriteAccount favouriteAccount;
	AddAccountInputDto addAccountInputDto;
	RestTempleteDto restTempleteDto;

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

		restTempleteDto = new RestTempleteDto();
		restTempleteDto.setBankName("sample");
		restTempleteDto.setStatusCode(201);
	}

	@Test(expected = IngBankException.class)
	public void addAccount1() {
		addAccountInputDto.setAccountName("sai");
		addAccountInputDto.setIbanNumber("1@3456");
		addFavorateAccountImpl.addAccount(addAccountInputDto);

	}

	
	@Test(expected = IngBankException.class)
	public void addAccountNegative3() {
		addAccountInputDto.setAccountName("sai");
		addAccountInputDto.setIbanNumber("163456");
		addFavorateAccountImpl.addAccount(addAccountInputDto);

	}

	@Test(expected = IngBankException.class)
	public void addAccountTestNegative() {
		addAccountInputDto.setAccountName("sai@#");
		addFavorateAccountImpl.addAccount(addAccountInputDto);

	}

	@Test(expected = IngBankException.class)
	public void addAccount2() {
		addAccountInputDto.setIbanNumber("1@34567");
		addFavorateAccountImpl.addAccount(addAccountInputDto);

	}

	@Test
	public void nameValidate() {
		addAccountInputDto.setIbanNumber("1@34567");
		boolean actul = addFavorateAccountImpl.nameValidation("10000000000000000000");

		Assert.assertEquals(true, actul);
	}

	@Test
	public void nameValidate2() {
		addAccountInputDto.setIbanNumber("1@34567");
		boolean actul = addFavorateAccountImpl.nameValidation("100@00000000000000000");

		Assert.assertEquals(false, actul);
	}

	@Test
	public void ibanValidate() {
		addAccountInputDto.setIbanNumber("1@34567");
		boolean actul = addFavorateAccountImpl.ibanValidation("10000000000000000000");

		Assert.assertEquals(true, actul);
	}

}
