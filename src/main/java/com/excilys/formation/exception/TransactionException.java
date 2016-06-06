package com.excilys.formation.exception;

/**
 * Runtime exception that is thrown when a critical error occured during a
 * transaction process.
 * 
 * @author neoware
 *
 */
public class TransactionException extends RuntimeException {

	private static final long serialVersionUID = 3896524694977544801L;

	public TransactionException(String message, Exception e) {
		super(message, e);
	}

	public TransactionException(String message) {
		super(message);
	}
}