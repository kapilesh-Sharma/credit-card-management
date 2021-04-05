package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.domain.CreditCardEntity;
import com.exception.CreditCardException;

@Component
public class CardValidator {

	private static final Logger log = LoggerFactory.getLogger(CardValidator.class);
	private static final String REGEX_CARD_NUMBER = "^[0-9]{10,19}$";
	private static final String REGEX_NAME = "^([a-zA-z\\s]{2,20})$";

	/** Credit card input validations */
	public void cardInputValidation(CreditCardEntity creditCard) throws CreditCardException {

		log.debug("Credit Card input validation ");
		if (!CardValidator.validationByLuhn(creditCard.getCardNumber())
				|| !(creditCard.getCardNumber()).matches(REGEX_CARD_NUMBER)) {
			throw new CreditCardException(CreditCardException.INCORRECT_CARD_NUMBER);
		}
		//todo need to check whether other details are mandatory or not
		if (creditCard.getName().isEmpty() || !creditCard.getName().matches(REGEX_NAME)) {
			throw new CreditCardException(CreditCardException.INCORRECT_NAME_ON_CARD);
		}
		if (creditCard.getBalance() != 0) {
			throw new CreditCardException(CreditCardException.INCORRECT_CARD_BALANCE);
		}
		log.debug("Credit Card input validated successfully ");
	}

	/** Luhn check for Credit card number validity */
	private static boolean validationByLuhn(String cardNumber) {
		int sumOfCardNumber = 0;
		int[] cardNumberArray = new int[cardNumber.length()];
		for (int i = 0; i < cardNumber.length(); i++) {
			cardNumberArray[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
		}
		for (int i = cardNumberArray.length - 2; i >= 0; i = i - 2) {
			int evenDigits = cardNumberArray[i];
			evenDigits = evenDigits * 2;
			if (evenDigits > 9) {
				evenDigits = evenDigits % 10 + 1;
			}
			cardNumberArray[i] = evenDigits;
		}
		for (int i = 0; i < cardNumberArray.length; i++) {
			sumOfCardNumber += cardNumberArray[i];
		}

		if (sumOfCardNumber % 10 == 0) {
			log.info(" Valid credit card number -" + cardNumber);
			return true;
		} else {
			log.error(" Invalid credit card number -" + cardNumber);
			return false;
		}
	}
}
