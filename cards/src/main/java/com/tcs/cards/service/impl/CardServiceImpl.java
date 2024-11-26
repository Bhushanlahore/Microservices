package com.tcs.cards.service.impl;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tcs.cards.constants.CardConstants;
import com.tcs.cards.dto.CardDto;
import com.tcs.cards.entity.Cards;
import com.tcs.cards.exception.CardAlreadyExistException;
import com.tcs.cards.exception.ResourceNotFoundException;
import com.tcs.cards.mapper.CardMapaper;
import com.tcs.cards.repository.CardsRepository;
import com.tcs.cards.service.ICardService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService{
	
	private CardsRepository cardsRepository;

	@Override
	public void createCard(String mobileNumber) {
		
		Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);
				
		if(card.isPresent()) {
			throw new CardAlreadyExistException("Card already avaialable with this mobile number");
		}
		
		Cards savedCard = cardsRepository.save(newCard(mobileNumber));
		
	}
	
	private Cards newCard(String mobileNumber) {
		
		Cards cards =  new Cards();
		long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
		cards.setCardType(CardConstants.CREDIT_CARD);
		cards.setCardNumber(Long.toString(randomCardNumber));
		cards.setMobileNumber(mobileNumber);
		cards.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
		cards.setAmountUsed(0);
		cards.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
		
		return cards;
		
	}
	

	@Override
	public CardDto fetchCard(String mobileNumber) {
		// TODO Auto-generated method stub
		
		Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
				()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		
		
		return CardMapaper.mapToCardsDto(cards, new CardDto());
	}

	@Override
	public boolean updateCard(CardDto cardDto) {
		
		boolean isUpdated = false;
		
		if(cardDto !=null) {
			
			Cards cards = cardsRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
					()-> new ResourceNotFoundException("Card", "cardNumber", cardDto.getCardNumber()));
			
			CardMapaper.mapToCards(cardDto, cards);
			
			cardsRepository.save(cards);
			isUpdated =true;
			
			return isUpdated;
		}
		
		return isUpdated;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {
		// TODO Auto-generated method stub		
		Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
				()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		
		cardsRepository.deleteById(card.getCardId());
		
		return true;
	}
	

}
