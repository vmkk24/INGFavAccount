package com.hcl.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDetailsDto implements Serializable{

	private static final long serialVersionUID = 1062869082761356871L;
	
	private Integer statusCode;
	private String message;
	private String name;

}
