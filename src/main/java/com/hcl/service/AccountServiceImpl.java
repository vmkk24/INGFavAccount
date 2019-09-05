package com.hcl.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dto.RequestEditDto;
import com.hcl.dto.ResponseEditDto;
import com.hcl.entity.FavouriteAccount;
import com.hcl.exception.IngBankException;
import com.hcl.repository.FavouriteAccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	FavouriteAccountRepository favouriteAccountRepository;

	@Override
	public ResponseEditDto editFavouriteAccount(RequestEditDto requestEditDto) {
		ResponseEditDto responseEditDto = new ResponseEditDto();
		Optional<FavouriteAccount> favouriteAccount = favouriteAccountRepository
				.findById(requestEditDto.getFavouriteAccountId());
		if (favouriteAccount.isPresent()) {
			if (requestEditDto.getAccountName() != null && requestEditDto.getIbanNumber() != null) {
				if (!nameValidation(requestEditDto.getAccountName()))
					throw new IngBankException(" name only ' and - special charecters only allowed");
				if (!ibanValidation(requestEditDto.getIbanNumber()))
					throw new IngBankException("no  special charecters allowed in iban");
				if (!ibanLengthValidation(requestEditDto.getIbanNumber()))
					throw new IngBankException("account number should be less than 20 characters");
				favouriteAccount.get().setIbanNumber(requestEditDto.getIbanNumber());
				favouriteAccount.get().setAccountName(requestEditDto.getAccountName());
			} else if (requestEditDto.getAccountName() != null) {
				if (!nameValidation(requestEditDto.getAccountName()))
					throw new IngBankException(" name only ' and - special charecters only allowed");
				favouriteAccount.get().setAccountName(requestEditDto.getAccountName());

			} else if (requestEditDto.getIbanNumber() != null) {

				if (!ibanValidation(requestEditDto.getIbanNumber()))
					throw new IngBankException("no  special charecters allowed in iban");
				if (!ibanLengthValidation(requestEditDto.getIbanNumber()))
					throw new IngBankException("account number should be less than 20 characters");
				favouriteAccount.get().setIbanNumber(requestEditDto.getIbanNumber());
			} else {
				throw new IngBankException("enter proper fields");
			}
			favouriteAccountRepository.save(favouriteAccount.get());
			responseEditDto.setMessage("update Successfully");
			responseEditDto.setStatusCode(200);
			return responseEditDto;
		} else {
			responseEditDto.setMessage("account details not available");
			responseEditDto.setStatusCode(404);
			return responseEditDto;
		}

	}

	@Override
	public ResponseEditDto deleteFavouriteAccount(Integer favouriteAccountId) {
		Optional<FavouriteAccount> favouriteAccount = favouriteAccountRepository.findById(favouriteAccountId);
		if(!favouriteAccount.isPresent()) {
		throw new  IngBankException("no faverate accounts");
		}
		
		FavouriteAccount favouriteAccount2 = new FavouriteAccount();
		
		favouriteAccount2.setAccountName(favouriteAccount.get().getAccountName());
		favouriteAccount2.setCustomerId(favouriteAccount.get().getCustomerId());
		favouriteAccount2.setFavouriteAccountId(favouriteAccount.get().getFavouriteAccountId());
		favouriteAccount2.setIbanNumber(favouriteAccount.get().getIbanNumber());
		favouriteAccount2.setStatus("INACTIVE");
		favouriteAccountRepository.save(favouriteAccount2);
		ResponseEditDto responseEditDto = new ResponseEditDto();
		responseEditDto.setMessage("delete successfully");
		responseEditDto.setStatusCode(200);
		return responseEditDto;
	}

	@Override
	public boolean ibanValidation(String iban) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		for (int i = 0; i < iban.length(); i++) {
			String c = "" + iban.charAt(i);
			c = c.trim();
			Matcher matcher2 = pattern.matcher(c);
			if (!matcher2.matches()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean nameValidation(String name) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		for (int i = 0; i < name.length(); i++) {
			String c = "" + name.charAt(i);
			c = c.trim();
			Matcher matcher2 = pattern.matcher(c);
			if (!matcher2.matches()) {
				if ((!c.equalsIgnoreCase("'")) && (!c.equalsIgnoreCase("-"))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean ibanLengthValidation(String iban) {
		int length = iban.length();
		if (length > 20)
			return false;
		return true;
	}

}
