package com.hcl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.entity.FavouriteAccount;

public interface FavouriteAccountRepository extends JpaRepository<FavouriteAccount, Integer> {

	public List<FavouriteAccount> findByCustomerId(Integer customerId);

}
