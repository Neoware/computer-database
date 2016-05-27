package com.excilys.formation.validator;

import com.excilys.formation.entity.Computer;
import com.excilys.formation.utils.ReturnInformation;

public class ComputerEntityValidator implements Validator<Computer> {

	@Override
	public ReturnInformation isValid(Computer toVerify) {
		ReturnInformation returnInformation = new ReturnInformation();
		validateComputerName(returnInformation, toVerify);
		validateDate(returnInformation, toVerify);
		validateCompanyName(returnInformation, toVerify);
		validateCompany(returnInformation, toVerify);
		return returnInformation;
	}

	private void validateCompany(ReturnInformation returnInformation, Computer toVerify) {

	}

	private void validateCompanyName(ReturnInformation returnInformation, Computer toVerify) {

	}

	private void validateDate(ReturnInformation returnInformation, Computer toVerify) {

	}

	private void validateComputerName(ReturnInformation returnInformation, Computer toVerify) {

	}

}
