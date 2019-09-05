package com.hcl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

	public List<Bank> findByBankCode(Integer bankCode);

}
