package com.excilys.formation.validator;

import org.apache.commons.lang3.StringUtils;

import com.excilys.formation.util.ReturnInformation;

public class PageRequestValidator {
	private ReturnInformation returnInformation;

	public PageRequestValidator() {

	}

	public PageRequestValidator(ReturnInformation returnInformation) {
		this.returnInformation = returnInformation;
	}

	/**
	 * 
	 * @param page
	 * @return
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
