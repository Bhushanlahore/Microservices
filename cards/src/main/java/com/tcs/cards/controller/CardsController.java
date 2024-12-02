package com.tcs.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.cards.constants.CardConstants;
import com.tcs.cards.dto.CardDto;
import com.tcs.cards.dto.ResponseDto;
import com.tcs.cards.service.ICardService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class CardsController {

	
	private ICardService iCardService;

	@PostMapping("/create/{number}")
	public ResponseEntity<ResponseDto> createCard(@PathVariable("number") String mobileNumber){
		
		iCardService.createCard(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
	}
	
	
	@GetMapping("/fetch")
	public ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber){
		
		CardDto fetchCard = iCardService.fetchCard(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(fetchCard);	
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateCard(@RequestBody CardDto cardDto){
		
		boolean isUpdated = iCardService.updateCard(cardDto);
		
		if(isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
			
		}else {
			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber){
		
		boolean deleteCard = iCardService.deleteCard(mobileNumber);
		
		if(deleteCard) {
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
		}else {
			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
		}
	}
}
