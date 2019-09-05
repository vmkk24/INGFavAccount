package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.FavouriteAccountDto;
import com.hcl.service.FavouriteAccountServiceImpl;

@RestController
public class FavouriteAccountController {

	@Autowired
	FavouriteAccountServiceImpl favouriteAccountServiceImpl;

	@GetMapping("/favouriteAccounts/{customerId}")
	public ResponseEntity<List<FavouriteAccountDto>> getFavouriteAccountList(@PathVariable Integer customerId) {

		return new ResponseEntity<>(favouriteAccountServiceImpl.getAccountsList(customerId), HttpStatus.OK);
	}

}
