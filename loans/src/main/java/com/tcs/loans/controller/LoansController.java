package com.tcs.loans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.Pattern;

import com.tcs.loans.constants.LoansConstants;
import com.tcs.loans.dto.LoansDto;
import com.tcs.loans.dto.ResponseDto;
import com.tcs.loans.service.ILoansService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LoansController {
	
	private ILoansService iLoansService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createLoan(@RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber){
		
		iLoansService.createLoan(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
	}
	
	
	@GetMapping("/fetch")
	public ResponseEntity<LoansDto> fetchLoan(@RequestParam String mobileNumber){
		
		LoansDto fetchLoan = iLoansService.fetchLoan(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(fetchLoan);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateCard(@RequestBody LoansDto loansDto){
		
		boolean isUpdated = iLoansService.updateLoan(loansDto);
		
		if(isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
			
		}else {
			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
		}
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber){
		
		boolean deleteCard = iLoansService.deleteLoan(mobileNumber);
		
		if(deleteCard) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
		}else {
			
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
		}
	}
	
	

}
