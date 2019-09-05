package com.hcl.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.RequestEditDto;
import com.hcl.dto.ResponseEditDto;
import com.hcl.service.AccountService;

@RestController
@CrossOrigin(origins = { "*", "*/" }, allowedHeaders = { "*", "*/" })
public class AccountController {

	
	private static Logger logger = Logger.getLogger(AccountController.class);
	@Autowired
	AccountService accountService;

	@PutMapping("/favouriteAccount")
	public ResponseEntity<ResponseEditDto> editFavouriteAccount(@RequestBody RequestEditDto requestEditDto) {

		logger.info("update the account details");
		return new ResponseEntity<>(accountService.editFavouriteAccount(requestEditDto), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/favouriteAccounts/{favouriteAccountId}")
	public ResponseEntity<ResponseEditDto> deleteFavouriteAccount(@PathVariable Integer favouriteAccountId){
		logger.info("delete the account details");
		return new ResponseEntity<>(accountService.deleteFavouriteAccount(favouriteAccountId),HttpStatus.OK);
	}

}
