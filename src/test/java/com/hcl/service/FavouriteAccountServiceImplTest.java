package com.hcl.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.dto.FavouriteAccountDto;
import com.hcl.entity.Bank;
import com.hcl.entity.Customer;
import com.hcl.entity.FavouriteAccount;
import com.hcl.exception.IngBankException;
import com.hcl.repository.BankRepository;
import com.hcl.repository.FavouriteAccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class FavouriteAccountServiceImplTest {

	@Mock
	FavouriteAccountRepository favouriteAccountRepository;

	@Mock 
	BankRepository bankRepository;

	@InjectMocks
	FavouriteAccountServiceImpl favouriteAccountService;

	@Test
	public void testGetAccountsList() {

		List<FavouriteAccount> favouriteAccountList = new ArrayList<>();

		List<Bank> bankList = new ArrayList<>();
		List<FavouriteAccountDto> favouriteAccountDtoList = new ArrayList<>();

		FavouriteAccountDto favouriteAccountDto = new FavouriteAccountDto();
		favouriteAccountDto.setAccountName("shiva");
		favouriteAccountDto.setBankName("Denver Bank");
		favouriteAccountDto.setFavouriteAccountId(1);
		favouriteAccountDto.setIbanNumber("ES502123495444432332");
		favouriteAccountDtoList.add(favouriteAccountDto);

		FavouriteAccountDto favouriteAccountDto2 = new FavouriteAccountDto();
		favouriteAccountDto2.setAccountName("kumar");
		favouriteAccountDto2.setBankName("Moscow Bank");
		favouriteAccountDto2.setFavouriteAccountId(1);
		favouriteAccountDto2.setIbanNumber("ES502124495444432332");
		favouriteAccountDtoList.add(favouriteAccountDto2);

		Bank bank = new Bank();
		bank.setBankId(1);
		bank.setBankName("Denver Bank");
		bank.setBankCode(2124);
		bankList.add(bank);

		Bank bank2 = new Bank();
		bank2.setBankId(2);
		bank2.setBankName("Moscow Bank");
		bank2.setBankCode(2124);
		bankList.add(bank2);

		FavouriteAccount favouriteAccount = new FavouriteAccount();
		favouriteAccount.setAccountName("shiva");
		favouriteAccount.setCustomerId(1);
		favouriteAccount.setFavouriteAccountId(1);
		favouriteAccount.setIbanNumber("ES502124495444432332");
		favouriteAccount.setStatus("ACTIVE");
		favouriteAccountList.add(favouriteAccount);

		Customer customer = new Customer();
		customer.setCustomerId(1);
		Mockito.when(
				favouriteAccountRepository.findByCustomerIdAndStatus(Mockito.anyInt(), Mockito.any(), Mockito.any()))
				.thenReturn(favouriteAccountList);

		Mockito.when(bankRepository.findByBankCode(Mockito.anyInt())).thenReturn(bankList);

		List<FavouriteAccountDto> actualValue = favouriteAccountService.getAccountsList(customer.getCustomerId());

		assertEquals(favouriteAccountDtoList.size(), actualValue.size());
	}

	@Test(expected = IngBankException.class)
	public void testGetAccountsListElse() {

		List<FavouriteAccount> favouriteAccountList = new ArrayList<>();

		List<Bank> bankList = new ArrayList<>();
		List<FavouriteAccountDto> favouriteAccountDtoList = new ArrayList<>();

		FavouriteAccountDto favouriteAccountDto = new FavouriteAccountDto();
		favouriteAccountDto.setAccountName("shiva");
		favouriteAccountDto.setBankName("Denver Bank");
		favouriteAccountDto.setFavouriteAccountId(1);
		favouriteAccountDto.setIbanNumber("ES50 2123 4954 4443 2332");
		favouriteAccountDtoList.add(favouriteAccountDto);

		FavouriteAccountDto favouriteAccountDto2 = new FavouriteAccountDto();
		favouriteAccountDto2.setAccountName("kumar");
		favouriteAccountDto2.setBankName("Moscow Bank");
		favouriteAccountDto2.setFavouriteAccountId(1);
		favouriteAccountDto2.setIbanNumber("ES50 2124 4954 4443 2332");
		favouriteAccountDtoList.add(favouriteAccountDto2);

		Bank bank = new Bank();
		bank.setBankId(1);
		bank.setBankName("Denver Bank");
		bank.setBankCode(2124);
		bankList.add(bank);

		Bank bank2 = new Bank();
		bank2.setBankId(2);
		bank2.setBankName("Moscow Bank");
		bank2.setBankCode(2124);
		bankList.add(bank2);

		FavouriteAccount favouriteAccount = new FavouriteAccount();
		favouriteAccount.setAccountName("shiva");
		favouriteAccount.setCustomerId(1);
		favouriteAccount.setFavouriteAccountId(1);
		favouriteAccount.setIbanNumber("ES50 2124 4954 4443 2332");
		favouriteAccount.setStatus("ACTIVE");
		favouriteAccountList.add(favouriteAccount);

		Customer customer = new Customer();
		customer.setCustomerId(1);

		// Mockito.when(bankRepository.findByBankCode(Mockito.anyInt())).thenReturn(bankList);

		favouriteAccountService.getAccountsList(customer.getCustomerId());

	}

}
