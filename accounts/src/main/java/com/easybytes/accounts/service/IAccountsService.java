package com.easybytes.accounts.service;

import com.easybytes.accounts.dto.CustomerDto;

public interface IAccountsService {
	
	 void createAccount(CustomerDto customerDto);
	 
	 CustomerDto fetchAccount(String mobileNo);
	 
	 boolean updateAccount(CustomerDto customerDto);
	 
	 boolean deleteAccount(String mobileNumber);

}
