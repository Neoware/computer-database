package com.excilys.formation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.util.ReturnInformation;

public class ComputerDtoValidator implements Validator<ComputerDTO> {

	private static final int LIMIT_SIZE = 255;

	@Override
	public ReturnInformation isValid(ComputerDTO toVerify) {
		ReturnInformation returnInformation = new ReturnInformation();
		validateComputerName(returnInformation, toVerify);
		validateDate(returnInformation, toVerify);
		validateCompanyName(returnInformation, toVerify);
		return returnInformation;
	}

	private void validateCompanyName(ReturnInformation returnInformation, ComputerDTO toVerify) {
		if (toVerify.getCompanyName() != null) {
			if (toVerify.getCompanyName().length() > LIMIT_SIZE) {
				returnInformation.addMessage("Company name is too big, enter 250 characters or less\n");
				returnInformation.setSuccess(false);
			}
		}
	}

	private void validateDate(ReturnInformation returnInformation, ComputerDTO toVerify) {
		String regex = "^(?:(?:31(-)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(-)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(-)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(-)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher;
		if (toVerify.getDiscontinued() != null && !toVerify.getDiscontinued().isEmpty()) {
			matcher = pattern.matcher(toVerify.getDiscontinued());
			if (!matcher.find()) {
				returnInformation.addMessage("Bad date format for discontinued\n");
				returnInformation.setSuccess(false);
			}
		}
		if (toVerify.getIntroduced() != null && !toVerify.getIntroduced().isEmpty()) {
			matcher = pattern.matcher(toVerify.getIntroduced());
			if (!matcher.find()) {
				returnInformation.addMessage("Bad date format for introduced\n");
				returnInformation.setSuccess(false);
			}
		}
	}

	private void validateComputerName(ReturnInformation returnInformation, ComputerDTO toVerify) {
		String name = toVerify.getName();
		if (name == null) {
			returnInformation.addMessage("Computer name can't be null\n");
			returnInformation.setSuccess(false);
		}
		if (name.isEmpty()) {
			returnInformation.addMessage("Computer name can't be empty\n");
			returnInformation.setSuccess(false);
		}
		if (name.length() > LIMIT_SIZE) {
			returnInformation.addMessage("Computer name is too big, enter 250 characters or less\n");
			returnInformation.setSuccess(false);
		}
	}

}
