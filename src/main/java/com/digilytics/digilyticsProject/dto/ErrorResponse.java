package com.digilytics.digilyticsProject.dto;
/**
 * @author akash
 *
 */
public class ErrorResponse {
	private String email;
	private boolean validationFlage;
	private String role;
	private String error;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isValidationFlage() {
		return validationFlage;
	}
	public void setValidationFlage(boolean validationFlage) {
		this.validationFlage = validationFlage;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	

}
