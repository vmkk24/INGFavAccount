package com.hcl.service;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.dto.AddAccountInputDto;
import com.hcl.entity.FavouriteAccount;
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
		favouriteAccount=new FavouriteAccount();
		favouriteAccount.setAccountName("sairam");
		favouriteAccount.setCustomerId(1);
		favouriteAccount.setIbanNumber("123123456789234567834567");
		addAccountInputDto=new AddAccountInputDto();
		addAccountInputDto.setCustomerId(1);
		addAccountInputDto.setAccountName(favouriteAccount.getAccountName());
		addFavorateAccountImpl.addAccount(addAccountInputDto);
		
	}

	@Test
	public void testAddAccount() {
		favouriteAccountRepository.save(favouriteAccount);
	}

	@Test
	public void testNameValidation() {
		fail("Not yet implemented");
	}

	@Test
	public void testIbanValidation() {
		fail("Not yet implemented");
	}

}
