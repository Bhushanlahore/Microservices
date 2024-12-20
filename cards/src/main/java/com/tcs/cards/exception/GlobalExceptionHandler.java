package com.tcs.cards.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tcs.cards.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest){
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), 
				HttpStatus.INTERNAL_SERVER_ERROR, 
				exception.getMessage(),
				LocalDateTime.now());
		
		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, 
																				WebRequest webRequest){
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR, 
				exception.getMessage(), 
				LocalDateTime.now());
		
		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CardAlreadyExistException.class)
	public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistException(CardAlreadyExistException exception, 
																				WebRequest webRequest){
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), 
				HttpStatus.INTERNAL_SERVER_ERROR, 
				exception.getMessage(), 
				LocalDateTime.now());
		
		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
