package com.hcl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dto.FavouriteAccountDto;
import com.hcl.entity.Bank;
import com.hcl.entity.FavouriteAccount;
import com.hcl.repository.BankRepository;
import com.hcl.repository.FavouriteAccountRepository;

@Service
public class FavouriteAccountServiceImpl implements FavouriteAccountService {

	@Autowired
	FavouriteAccountRepository favouriteAccountRepository;

	@Autowired
	BankRepository bankRepository;

	@Override
	public List<FavouriteAccountDto> getAccountsList(Integer customerId) {

		FavouriteAccountDto favouriteAccountDto = null;

		List<FavouriteAccountDto> favouriteAccountDtoList = new ArrayList<>();

		List<FavouriteAccount> favouriteAccountList = favouriteAccountRepository.findByCustomerId(customerId);

		for (FavouriteAccount favouriteAccount : favouriteAccountList) {

			favouriteAccountDto = new FavouriteAccountDto();

			favouriteAccountDto.setIbanNumber(favouriteAccount.getIbanNumber());
			favouriteAccountDto.setAccountName(favouriteAccount.getAccountName());
			favouriteAccountDto.setFavouriteAccountId(favouriteAccount.getFavouriteAccountId());

			String ibanNo = favouriteAccount.getIbanNumber();
			String ibanList[] = ibanNo.split(" ");
			String ibanNumber = ibanList[1];
			Integer bankCode = Integer.parseInt(ibanNumber);
			List<Bank> bankList = bankRepository.findByBankCode(bankCode);
			for (Bank bank : bankList) {
				favouriteAccountDto.setBankName(bank.getBankName());
				favouriteAccountDtoList.add(favouriteAccountDto);
			}

		}

		return favouriteAccountDtoList;
	}

}
