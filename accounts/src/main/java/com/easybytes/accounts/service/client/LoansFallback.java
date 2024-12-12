package com.easybytes.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.easybytes.accounts.dto.LoansDto;

@Component
public class LoansFallback implements LoansFeignClient{

	@Override
	public ResponseEntity<LoansDto> fetchLoan(String correlationId, String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
