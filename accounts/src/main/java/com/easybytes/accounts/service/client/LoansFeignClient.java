 package com.easybytes.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.easybytes.accounts.dto.CardDto;
import com.easybytes.accounts.dto.LoansDto;


@FeignClient("loans")
public interface LoansFeignClient {

	@GetMapping(value = "/api/fetch", consumes = "application/json")
	 ResponseEntity<LoansDto> fetchLoan(@RequestHeader("eazybank-correlation-id") String correlationId,@RequestParam String mobileNumber);
}
