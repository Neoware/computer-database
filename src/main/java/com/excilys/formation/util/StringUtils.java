package com.excilys.formation.util;

/**
 * Helper class to manipulate string
 * 
 * @author neoware
 *
 */
public class StringUtils {

	/**
	 * Check if a string is null or empty
	 * 
	 * @param toCheck
	 *            The string that need to be checked;
	 * @return true if the string is null or empty, false otherwise
	 */
	public static boolean isNullOrEmpty(String toCheck) {
		return (toCheck == null || toCheck.isEmpty());
	}
}
