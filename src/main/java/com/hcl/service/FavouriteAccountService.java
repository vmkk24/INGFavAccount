package com.hcl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.dto.FavouriteAccountDto;

@Service
public interface FavouriteAccountService {

	public List<FavouriteAccountDto> getAccountsList(Integer customerId);

}
