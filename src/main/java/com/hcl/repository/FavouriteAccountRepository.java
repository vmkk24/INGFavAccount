package com.hcl.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.entity.FavouriteAccount;

public interface FavouriteAccountRepository extends JpaRepository<FavouriteAccount, Integer> {

	public List<FavouriteAccount> findByCustomerIdAndStatus(Integer customerId, String status, Pageable pageable);

}
