package com.hcl.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RequestEditDto {
	
	private String ibanNumber;
	private String accountName;
	private Integer favouriteAccountId;

	
}
