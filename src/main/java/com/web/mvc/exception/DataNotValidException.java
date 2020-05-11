package com.web.mvc.exception;
/**
 * Custom Exception
 * 
 */
public class DataNotValidException extends RuntimeException {

	private static final long serialVersionUID = -5732275310714915330L;

	public DataNotValidException(String msg){
		super(msg);
	}
}
