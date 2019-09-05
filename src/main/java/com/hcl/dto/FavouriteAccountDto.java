package com.hcl.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FavouriteAccountDto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String ibanNumber;
	private String accountName;
	private String bankName;
	private Integer favouriteAccountId;
	

}
