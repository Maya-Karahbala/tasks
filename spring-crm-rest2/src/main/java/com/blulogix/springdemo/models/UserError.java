package com.blulogix.springdemo.models;

public class UserError  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
