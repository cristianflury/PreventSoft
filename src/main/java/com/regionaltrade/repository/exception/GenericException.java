package com.regionaltrade.repository.exception;

public class GenericException extends Exception {

	private static final long serialVersionUID = -5372085754118096665L;

	public GenericException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public GenericException(String message) {
		super(message);
		
	}

}
