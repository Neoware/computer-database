package com.excilys.formation.service;

import java.util.List;

import com.excilys.formation.entity.Computer;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.ComputerDAO;

public class ComputerService {

	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;

	public ComputerService() {
		computerDAO = new ComputerDAO();
		companyDAO = new CompanyDAO();
	}

	public List<Computer> getAllComputers() {
		List <Computer> computers = computerDAO.getAll();
		return computers;
	}
	
	public Computer getComputerDetails(Long id){
		Computer computer = computerDAO.find(id);
		return computer;
	}
	
	public void createComputer(Computer toCreate){
		computerDAO.create(toCreate);
	}
	
	public void updateComputer(Computer toUpdate){
		
	}
	
	public void deleteComputer(){
		
	}
	
	
	
	
}
