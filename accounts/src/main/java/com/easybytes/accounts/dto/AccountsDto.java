package com.easybytes.accounts.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data 
public class AccountsDto {
	
	@NotNull(message = "Account no can not be null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Account no must be in 10 digit")
    private Long accountNumber;

	@NotNull(message = "Account type can not be null or empty")
    private String accountType;

	@NotNull(message = "Branch Address type can not be null or empty")
    private String branchAddress;


}
