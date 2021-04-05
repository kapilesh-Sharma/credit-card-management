package com.exception;

/** Application Exception message details bean */

public class ExceptionDetails {

	private String ExceptionMessage;
	private String URI;

	public String getExceptionMessage() {
		return ExceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		ExceptionMessage = exceptionMessage;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public ExceptionDetails(String exceptionMessage, String uRI) {
		super();
		ExceptionMessage = exceptionMessage;
		URI = uRI;
	}

	@Override
	public String toString() {
		return "ExceptionDetails [ExceptionMessage=" + ExceptionMessage + ", URI=" + URI + "]";
	}

}
