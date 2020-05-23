package com.digilytics.digilyticsProject.exception;
/**
 * @author Akash Singhal
 *
 */
public class CustomException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String email;

	public CustomException(String email, String message) {
		super(message);
		this.email = email;
	}
	
	public CustomException(String message) {
		super(message);
	}

	public CustomException(Throwable cause) {
		super(cause);
	}
	
	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getEmail() {
		return email;
	}
}
