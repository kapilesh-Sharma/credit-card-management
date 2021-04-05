package com.exception;

/** Card input related exception details mapping */
public class CreditCardException extends Exception {

	public static final String INCORRECT_NAME_ON_CARD = "Incorrect Name on Card";
	public static final String INCORRECT_CARD_NUMBER = "Invalid credit card number";
	public static final String INCORRECT_CARD_BALANCE = "Intial Balance should be zero";
	public static final String DUPLICATE_CARD_NUMBER = "Credit card number already exist";


	private static final long serialVersionUID = 3231213213131L;

	public CreditCardException() {
		super();
	}

	public CreditCardException(final String message) {
		super(message);
	}

	public CreditCardException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

}
