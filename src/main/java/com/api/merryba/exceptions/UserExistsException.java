package com.api.merryba.exceptions;

public class UserExistsException extends Exception{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8155704495437307134L;

	public UserExistsException(String message) {
		super(message);
	}
}
