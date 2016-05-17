package com.excilys.formation.service;

import com.excilys.formation.persistence.Computer;

public interface ComputerService {

	public void listAllComputers();
	
	public void showComputerDetails();
	
	public void createComputer();
	
	public void updateComputer();
	
	public void deleteComputer();
}
