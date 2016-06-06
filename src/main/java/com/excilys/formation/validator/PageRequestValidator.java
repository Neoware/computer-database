package com.excilys.formation.validator;

import org.apache.commons.lang3.StringUtils;

import com.excilys.formation.util.ReturnInformation;

/**
 * Validate some parameters of a HTTP request.
 * 
 * @author neoware
 *
 */
public class PageRequestValidator {
	private ReturnInformation returnInformation;

	public PageRequestValidator() {

	}

	public PageRequestValidator(ReturnInformation returnInformation) {
		this.returnInformation = returnInformation;
	}

	/**
	 * 
	 * @param pageNumber
	 *            The number of the page that has to be validated.
	 * @return true if the pageNumber is correct or empty/null and false
	 *         otherwise.
	 */
	public boolean validatePage(String pageNumber) {
		if (pageNumber != null && pageNumber.length() > 0) {
			if (StringUtils.isNumeric(pageNumber)) {
				return true;
			} else {
				returnInformation.addMessage("Error with page number");
				returnInformation.setSuccess(false);
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @param limit
	 *            The limit that has to be validated.
	 * @return true if the limit is correct or empty/null and false otherwise.
	 */
	public boolean validateLimit(String limit) {
		if (limit != null && limit.length() > 0) {
			if (StringUtils.isNumeric(limit)) {
				return true;
			} else {
				returnInformation.addMessage("Error with the size of the page");
				returnInformation.setSuccess(false);
				return false;
			}
		} else {
			return true;
		}
	}
}
