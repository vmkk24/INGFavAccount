package com.hcl.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.dto.FavouriteAccountDto;
import com.hcl.dto.RestTempleteDto;
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

	@Autowired
	RestTemplate restTemplate;

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

		String status = "ACTIVE";

		List<FavouriteAccountDto> favouriteAccountDtoList = new ArrayList<>();

		Pageable pageable = PageRequest.of(0, 5);
		List<FavouriteAccount> favouriteAccountList = favouriteAccountRepository.findByCustomerIdAndStatus(customerId,
				status, pageable);

		if (favouriteAccountList.isEmpty()) {
			throw new IngBankException("No Favourite Accounts added");
		} else {

			favouriteAccountList.stream().forEach(favouriteAccount -> {
				FavouriteAccountDto favouriteAccountDto = new FavouriteAccountDto();

				favouriteAccountDto.setIbanNumber(favouriteAccount.getIbanNumber());
				favouriteAccountDto.setAccountName(favouriteAccount.getAccountName());
				favouriteAccountDto.setFavouriteAccountId(favouriteAccount.getFavouriteAccountId());

				String ibanNo = favouriteAccount.getIbanNumber();

				ResponseEntity<RestTempleteDto> bankName = restTemplate
						.getForEntity("http://10.117.189.104:9094/ingbank/bank/" + ibanNo, RestTempleteDto.class);
				if (bankName.getBody().getStatusCode() != 200) {
					throw new IngBankException("Bank Name Not Existed");
				}
				logger.info("bank name :{}", bankName.getBody().getBankName());
				String bankNames = bankName.getBody().getBankName();

				favouriteAccountDto.setBankName(bankNames);

				favouriteAccountDtoList.add(favouriteAccountDto);

			});

			return favouriteAccountDtoList;
		}
	}
}
