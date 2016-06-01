package com.excilys.formation.util;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static String clean(String dirty) {
		if (dirty != null) {
			return escapeHtml4(dirty.trim());
		} else {
			return dirty;
		}
	}

	public static String getCleanParameter(String name, HttpServletRequest request) {
		return clean(request.getParameter(name));
	}
}
