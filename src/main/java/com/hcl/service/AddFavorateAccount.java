package com.hcl.service;

import com.hcl.dto.AddAccountInputDto;
import com.hcl.dto.AddAccountOutputDto;

public interface AddFavorateAccount {
	
	public AddAccountOutputDto addAccount(AddAccountInputDto addAccountInputDto);
	public  boolean ibanValidation(String iban);
	public  boolean nameValidation(String name);

}
