package com.excilys.formation.exception;

/**
 * Runtime exception that is thrown by the DAO when a critical error occured
 * that will lead to a 500 error.
 * 
 * @author neoware
 *
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 7530228258289161246L;

	public DaoException(String message, Exception e) {
		super(message, e);
	}

	public DaoException(String message) {
		super(message);
	}
}
