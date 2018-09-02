package com.sasi.spring.api.mockitodemo.exception;

public class EmployeeCustamizedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	public EmployeeCustamizedException() {
		super();
	}
	public EmployeeCustamizedException(String message) {
		super(message);
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
