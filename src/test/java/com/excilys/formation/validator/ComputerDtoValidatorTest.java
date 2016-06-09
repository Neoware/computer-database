package com.excilys.formation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.util.ReturnInformation;

public class ComputerDtoValidatorTest {

	@Test
	public void ValidateACorrectComputerDtoObjectWithJustAName() {
		ComputerDtoValidator computerDtoValidator = new ComputerDtoValidator();
		ComputerDTO computerDTO;
		computerDTO = new ComputerDTO();
		computerDTO.setName("Correct_Name");
		ReturnInformation returnInformation = computerDtoValidator.isValid(computerDTO);
		assertTrue(returnInformation.isSuccess());
	}

	@Test
	public void ValidateACorrectComputerDtoWithAllAttributesExceptId() {
		ComputerDtoValidator computerDtoValidator = new ComputerDtoValidator();
		ComputerDTO computerDTO;
		computerDTO = new ComputerDTO();
		computerDTO.setName("Correct_Name");
		computerDTO.setCompanyId("5");
		computerDTO.setCompanyName("Correct_Company");
		computerDTO.setIntroduced("21-08-1992");
		computerDTO.setDiscontinued("24-08-1992");
		ReturnInformation returnInformation = computerDtoValidator.isValid(computerDTO);
		assertTrue(returnInformation.isSuccess());
	}

	@Test
	public void ValidateABadComputerDtoWithEmptyName() {
		ComputerDtoValidator computerDtoValidator = new ComputerDtoValidator();
		ComputerDTO computerDTO;
		computerDTO = new ComputerDTO();
		computerDTO.setName("");
		ReturnInformation returnInformation = computerDtoValidator.isValid(computerDTO);
		assertFalse(returnInformation.isSuccess());
	}

	@Test
	public void ValidateABadComputerWithBadIntroducedDateFormatAfterDiscontinued() {
		ComputerDtoValidator computerDtoValidator = new ComputerDtoValidator();
		ComputerDTO computerDTO;
		computerDTO = new ComputerDTO();
		computerDTO.setName("name");
		computerDTO.setIntroduced("24/08/1992");
		ReturnInformation returnInformation = computerDtoValidator.isValid(computerDTO);
		assertTrue(returnInformation.isSuccess());
	}
}
