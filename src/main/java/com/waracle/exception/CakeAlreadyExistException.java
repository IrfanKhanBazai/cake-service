package com.waracle.exception;

public class CakeAlreadyExistException extends Exception{
	
	private static final long serialVersionUID = 1563230176159690217L;
	
	public CakeAlreadyExistException (String msg) {
		super(msg);
	}

}
