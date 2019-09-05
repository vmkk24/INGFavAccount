package com.hcl.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class AddAccountInputDto {
	
	private String ibanNumber;
	private String accountName;
	private Integer customerId;


}
