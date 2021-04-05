package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CardServiceExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handles invalid data input related exceptions
	 */
	@ExceptionHandler(CreditCardException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionDetails handleInputException(final CreditCardException cardException,
			final HttpServletRequest request) {
		log.error("CreditCardException: {}", cardException.getMessage());
		return new ExceptionDetails(cardException.getMessage(), request.getRequestURI());
	}

	/**
	 * Handles empty data with null pointer exceptions
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody ExceptionDetails handleEmptyDataExceptions(final MethodArgumentNotValidException ex,
			final HttpServletRequest request) {
		log.error("Empty or null input Exception: {}", ex);
		return new ExceptionDetails(ex.getMessage(), request.getRequestURI());
	}

	/**
	 * Handles empty data with null pointer exceptions
	 */
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody ExceptionDetails handleNullPointerExceptions(final NullPointerException ex,
			final HttpServletRequest request) {
		log.error("Null pointer  Exception: {}", ex);
		return new ExceptionDetails(ex.getMessage(), request.getRequestURI());
	}

	/**
	 * Handles all other server and application related exceptions
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionDetails handleAllGenericExceptions(final Exception ex,
			final HttpServletRequest request) {
		log.error("Genric Exception: {}", ex);
		return new ExceptionDetails(ex.getMessage(), request.getRequestURI());
	}

	/** -- All other application related exceptions can be handled here -- */
}
