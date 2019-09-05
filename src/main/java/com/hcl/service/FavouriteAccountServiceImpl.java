package com.hcl.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hcl.dto.FavouriteAccountDto;
import com.hcl.entity.Bank;
import com.hcl.entity.FavouriteAccount;
import com.hcl.exception.IngBankException;
import com.hcl.repository.BankRepository;
import com.hcl.repository.FavouriteAccountRepository;

/**
 * @author Shiva
 * 
 *         This class contains logic to get different account details
 * 
 *
 */

@Service
public class FavouriteAccountServiceImpl implements FavouriteAccountService {

	private static final Logger logger = LoggerFactory.getLogger(FavouriteAccountServiceImpl.class);

	@Autowired
	FavouriteAccountRepository favouriteAccountRepository;

	@Autowired
	BankRepository bankRepository;

	/**
	 * @param customerId
	 * 
	 * @return list of FavouriteAccountDto Objects
	 * 
	 *         This method contains logic to get favourite account list based on
	 *         customerId
	 * 
	 *
	 */

	@Override
	public List<FavouriteAccountDto> getAccountsList(Integer customerId) {
		logger.info("Inside FavouriteAccountServiceImpl customerId:{}", customerId);

		FavouriteAccountDto favouriteAccountDto = null;
		String status = "ACTIVE";

		List<FavouriteAccountDto> favouriteAccountDtoList = new ArrayList<>();

		Pageable pageable = PageRequest.of(0, 3);
		List<FavouriteAccount> favouriteAccountList = favouriteAccountRepository.findByCustomerIdAndStatus(customerId,
				status, pageable);

		if (favouriteAccountList.isEmpty()) {
			throw new IngBankException("No Favourite Accounts added");
		} else {
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

}
