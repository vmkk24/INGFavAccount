package com.hcl.service;

import org.springframework.stereotype.Service;

import com.hcl.dto.RequestEditDto;
import com.hcl.dto.ResponseEditDto;

@Service
public interface AccountService {

	public ResponseEditDto editFavouriteAccount(RequestEditDto requestEditDto);

	public ResponseEditDto deleteFavouriteAccount(Integer favouriteAccountId);
	
	public  boolean ibanValidation(String iban);
	
	public boolean ibanLengthValidation(String iban);
	public  boolean nameValidation(String name);
	
}
