package com.excilys.formation.service;

import org.junit.Test;

public class ComputerServiceTest {

	@Test
	public void ValidateABadComputerDtoWithEmptyName() {
		ComputerService computerService = ComputerService.getInstance();
		Long id = new Long(1);
		computerService.getById(id);
	}
}
