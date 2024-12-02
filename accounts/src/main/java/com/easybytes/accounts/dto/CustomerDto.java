package com.easybytes.accounts.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
	
		@NotNull(message = "The name shouble not be null or empty")
		@Size(min = 5, max = 30, message = "The length of name should be between 5 and 30")
	    private String name;

		@NotNull(message = "The name shouble not be null or empty")
	    private String email;

		@Pattern(regexp = "(^$|[0-9]{10})", message = "mobile no must be in 10 digit")
	    private String mobileNumber;
	    
	    private AccountsDto accountDto;

}
