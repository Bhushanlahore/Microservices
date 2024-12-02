package com.easybytes.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easybytes.accounts.dto.CardDto;


@FeignClient("cards")
public interface CardsFeignClient {

	@GetMapping(value = "/api/fetch", consumes = "application/json")
	 ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber);
}
