package com.excilys.formation.validator;

import com.excilys.formation.util.ReturnInformation;

/**
 * Validate if an object is correct or not
 * 
 * @author neoware
 *
 * @param <T>
 *            The type of entity that the validator is supposed to check
 */
public interface Validator<T> {
	/**
	 * Main method of the class that verify if the submitted object is correct
	 * or not.
	 * 
	 * @param toVerify
	 *            The object that need to be verify.
	 * @return A ReturnInformation object holding information about the process
	 */
	public ReturnInformation isValid(T toVerify);

}
