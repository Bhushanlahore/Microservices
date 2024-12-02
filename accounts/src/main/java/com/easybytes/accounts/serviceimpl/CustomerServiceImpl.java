package com.easybytes.accounts.serviceimpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easybytes.accounts.dto.AccountsDto;
import com.easybytes.accounts.dto.CardDto;
import com.easybytes.accounts.dto.CustomerDetailsDto;
import com.easybytes.accounts.dto.LoansDto;
import com.easybytes.accounts.entity.Accounts;
import com.easybytes.accounts.entity.Customer;
import com.easybytes.accounts.exception.ResourceNotFoundException;
import com.easybytes.accounts.mapper.AccountsMapper;
import com.easybytes.accounts.mapper.CustomerMapper;
import com.easybytes.accounts.repository.AccountsRepository;
import com.easybytes.accounts.repository.CustomerReposiroty;
import com.easybytes.accounts.service.ICustomerService;
import com.easybytes.accounts.service.client.CardsFeignClient;
import com.easybytes.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{
	

	private AccountsRepository accountsRepository;
	private CustomerReposiroty customerRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;

	@Override
	public CustomerDetailsDto fetchCustomerDetails(String number) {
		// TODO Auto-generated method stub
		
		Customer customer = customerRepository.findByMobileNumber(number)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNo", number));

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));
		
		CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
		customerDetailsDto.setAccountDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
		
		ResponseEntity<CardDto> fetchCardDetails = cardsFeignClient.fetchCardDetails(number);
		customerDetailsDto.setCardsDto(fetchCardDetails.getBody());
		
		ResponseEntity<LoansDto> fetchLoan = loansFeignClient.fetchLoan(number);
		customerDetailsDto.setLoansDto(fetchLoan.getBody());
		
		return customerDetailsDto;
	}

}
