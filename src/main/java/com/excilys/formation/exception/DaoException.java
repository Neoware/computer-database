package com.excilys.formation.exception;

public class DaoException extends RuntimeException {

	public DaoException(String message, Exception e) {
		super(message, e);
	}

	public DaoException(String message) {
		super(message);
	}
}
