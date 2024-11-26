package com.tcs.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.cards.entity.Cards;

import java.util.List;
import java.util.Optional;


public interface CardsRepository extends JpaRepository<Cards, Long> {
	
	Optional<Cards> findByMobileNumber(String mobileNumber);
	
	Optional<Cards> findByCardNumber(String cardNumber);

}
