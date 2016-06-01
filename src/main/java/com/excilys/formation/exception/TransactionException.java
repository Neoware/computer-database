package com.excilys.formation.exception;

public class TransactionException extends RuntimeException {

	public TransactionException(String message, Exception e) {
		super(message, e);
	}

	public TransactionException(String message) {
		super(message);
	}
}