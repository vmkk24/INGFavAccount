package com.hcl.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.dto.AddAccountInputDto;
import com.hcl.dto.AddAccountOutputDto;
import com.hcl.dto.RestTempleteDto;
import com.hcl.entity.FavouriteAccount;
import com.hcl.exception.IngBankException;
import com.hcl.repository.FavouriteAccountRepository;

/**
 * @author sairam add the favorite account service
 **/
@Service
public class AddFavorateAccountImpl implements AddFavorateAccount {

	@Autowired
	FavouriteAccountRepository favouriteAccountRepository;

	@Autowired
	RestTemplate restTemplate;

	static final Logger LOGGER = LoggerFactory.getLogger(AddFavorateAccountImpl.class);

	/**
	 * 
	 * add the favorite account service
	 * 
	 * @param addAccountInputDto json will contains
	 * 
	 **/

	@Override
	public AddAccountOutputDto addAccount(AddAccountInputDto addAccountInputDto) {

		LOGGER.info("AddFavorateAccountImpl -->addAccount");

		if (!nameValidation(addAccountInputDto.getAccountName()))
			throw new IngBankException(" name only ' and - special charecters only allowed");

		if (!ibanValidation(addAccountInputDto.getIbanNumber()))
			throw new IngBankException("no  special charecters allowed in iban");

		if (addAccountInputDto.getIbanNumber().length()!=20)
			throw new IngBankException("iban number lenth is 20");
		
		

		ResponseEntity<RestTempleteDto> bankName = restTemplate.getForEntity(
				"http://localhost:9094/ingbank/bank/" + addAccountInputDto.getIbanNumber(), RestTempleteDto.class);
		if (bankName.getBody().getStatusCode() != 200) {
			throw new IngBankException("bank Name not Existed");
		} 
		LOGGER.info("bank name :{}", bankName.getBody().getBankName());

		FavouriteAccount favouriteAccount = new FavouriteAccount();
		favouriteAccount.setAccountName(addAccountInputDto.getAccountName());
		favouriteAccount.setIbanNumber(addAccountInputDto.getIbanNumber());
		favouriteAccount.setCustomerId(addAccountInputDto.getCustomerId());
		favouriteAccount.setStatus("ACTIVE");
		favouriteAccountRepository.save(favouriteAccount); 
		
		AddAccountOutputDto addAccountOutputDto = new AddAccountOutputDto(); 
		addAccountOutputDto.setMessage("succsessfully added");
		addAccountOutputDto.setStatusCode(HttpStatus.CREATED.value());

		return addAccountOutputDto;

	} 

	@Override
	public boolean nameValidation(String name) {

		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");

		for (int i = 0; i < name.length(); i++) {

			String c = "" + name.charAt(i);
			c = c.trim();
			Matcher matcher2 = pattern.matcher(c);
			if (!matcher2.matches()) {
				LOGGER.info("special char c:{}", c);

				if ((!c.equalsIgnoreCase("'")) && (!c.equalsIgnoreCase("-"))) {
					return false;
				}

			}

		}

		return true;

	}

	@Override
	public boolean ibanValidation(String iban) {
		
		LOGGER.info("AddFavorateAccountImpl -->ibanValidation()");


		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");

		for (int i = 0; i < iban.length(); i++) {

			String c = "" + iban.charAt(i);
			c = c.trim();
			Matcher matcher2 = pattern.matcher(c);
			if (!matcher2.matches()) {
				LOGGER.info("special char in iban c:{}", c);

				return false;

			}

		}

		return true;

	}

}
