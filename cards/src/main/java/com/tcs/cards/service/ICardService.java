package com.tcs.cards.service;

import com.tcs.cards.dto.CardDto;

public interface ICardService {
	
	
	void createCard(String mobileNumber);
	
	CardDto fetchCard(String mobileNumber);

	boolean updateCard(CardDto cardDto);
	
	boolean deleteCard(String mobileNumber);
}
