package com.services;

import com.domain.CreditCardEntity;
import com.exception.CreditCardException;

public interface CreditCardService {

	Iterable<CreditCardEntity> getAllCards();

    void saveCard(CreditCardEntity cardEntity) throws CreditCardException;

    CreditCardEntity updateCard(String cardNumber, CreditCardEntity creditCardRequest) throws CreditCardException;




}
