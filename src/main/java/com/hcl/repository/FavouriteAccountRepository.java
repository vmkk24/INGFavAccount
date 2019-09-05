package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.entity.FavouriteAccount;

@Repository
public interface FavouriteAccountRepository  extends JpaRepository<FavouriteAccount, Integer>{


}
