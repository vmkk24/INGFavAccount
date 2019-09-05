package com.hcl.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto implements Serializable{

	private static final long serialVersionUID = 781066043703644994L;
	
	private Integer customerId;

}
