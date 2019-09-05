package com.hcl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.FavouriteAccountDto;
import com.hcl.service.FavouriteAccountService;

/**
 * @author Shiva
 * 
 *         This class contains logic to get different account details
 * 
 *
 */
 
@RestController
@CrossOrigin(origins = { "*", "*/" }, allowedHeaders = { "*", "*/" })
public class FavouriteAccountController {

	private static final Logger logger = LoggerFactory.getLogger(FavouriteAccountController.class);

	@Autowired
	FavouriteAccountService favouriteAccountService;

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
	@GetMapping("/favouriteAccounts/{customerId}")
	public ResponseEntity<List<FavouriteAccountDto>> getFavouriteAccountList(@PathVariable Integer customerId) {

		logger.info("Inside FavouriteAccountController customerId:{}", customerId);

		return new ResponseEntity<>(favouriteAccountService.getAccountsList(customerId), HttpStatus.OK);
	}

}
