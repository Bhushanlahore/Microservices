package com.easybytes.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easybytes.accounts.dto.CustomerDetailsDto;
import com.easybytes.accounts.service.ICustomerService;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
public class CustomerController {

	private ICustomerService iCustomerService;

	@GetMapping("fetchCustomerDetails")
	public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile no must be in 10 digit") String mobileNo) {

		CustomerDetailsDto fetchCustomerDetails = iCustomerService.fetchCustomerDetails(mobileNo);

		return ResponseEntity.status(HttpStatus.OK).body(fetchCustomerDetails);
	}

}
