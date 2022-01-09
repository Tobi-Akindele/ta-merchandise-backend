package com.ta.exceptions;

public class NotFoundException extends AbstractException {
	
	private static final long serialVersionUID = 1L;
	
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
