package com.easybytes.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.easybytes.accounts.dto.CardDto;


@FeignClient(name="cards", fallback = CardsFallback.class)
public interface CardsFeignClient {

	@GetMapping(value = "/api/fetch", consumes = "application/json")
	 ResponseEntity<CardDto> fetchCardDetails(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
