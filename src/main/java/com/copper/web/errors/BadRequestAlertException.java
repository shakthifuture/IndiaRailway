package com.copper.web.errors;

public class BadRequestAlertException extends RuntimeException {
	
	private static final long serialVersionUID = 7399509946990401519L;

	public BadRequestAlertException(String message) {
		super(message);
	}
	
	public BadRequestAlertException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
