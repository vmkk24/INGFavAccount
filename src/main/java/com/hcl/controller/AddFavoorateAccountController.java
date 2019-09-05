package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.AddAccountInputDto;
import com.hcl.dto.AddAccountOutputDto;
import com.hcl.service.AddFavorateAccount;

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
public class AddFavoorateAccountController {

	@Autowired
	AddFavorateAccount addFavorateAccount;

	
	@PostMapping("/favouriteAccounts")
	public ResponseEntity<AddAccountOutputDto> addAccount(@RequestBody AddAccountInputDto addAccountInputDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(addFavorateAccount.addAccount(addAccountInputDto));

	}

}
