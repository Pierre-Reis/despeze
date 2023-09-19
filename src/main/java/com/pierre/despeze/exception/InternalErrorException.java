package com.pierre.despeze.exception;

public class InternalErrorException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InternalErrorException() {
		
	}
	
	public InternalErrorException( String message) {
		super(message);
	}
	
	public InternalErrorException( Throwable throwable ) {
		super(throwable.getMessage());
	}
	
	public InternalErrorException( String message, Throwable throwable ) {
		super(message);
	}
}
