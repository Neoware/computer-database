package com.excilys.formation.util;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import javax.servlet.http.HttpServletRequest;

/**
 * Helper class to extract data from an HTTP request.
 * 
 * @author neoware
 *
 */
public class RequestUtils {

	/**
	 * Method to clean a parameter by escaping and trimming it.
	 * 
	 * @param dirty
	 *            The string that need to be clean
	 * @return The cleaned string or null if the string was null
	 */
	public static String clean(String dirty) {
		if (dirty != null) {
			return escapeHtml4(dirty.trim());
		} else {
			return dirty;
		}
	}

	/**
	 * Helper method to get a clean parameter from the HTTP request.
	 * 
	 * @param name
	 *            name of the attribute that need to be retrieved from the
	 *            request.
	 * @param request
	 *            The HTTP request in which the attribute is searched.
	 * @return The cleaned parameter or null if it doesn't exist
	 */
	public static String getCleanParameter(String name, HttpServletRequest request) {
		return clean(request.getParameter(name));
	}
}
