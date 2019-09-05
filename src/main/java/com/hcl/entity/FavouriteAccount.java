package com.hcl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FavouriteAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer favouriteAccountId;
	private String ibanNumber;
	private String accountName;
	private String status;
	private Integer customerId;

}
