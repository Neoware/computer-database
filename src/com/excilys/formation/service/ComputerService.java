package com.excilys.formation.service;

import com.excilys.formation.persistence.Computer;
import com.excilys.formation.persistence.ComputerDAO;

public class ComputerService {
	
	private ComputerDAO computerDAO;

	public ComputerService(){
		computerDAO = new ComputerDAO();
	}
	
	public void listAllComputers(){
		computerDAO.getAll();
	}
	
	public void showComputerDetails(int id){
		Computer temp = computerDAO.find(id);
		System.out.println(temp);
	}
	
	public void createComputer(Computer toCreate){
		computerDAO.create(toCreate);
	}
	
	public void updateComputer(Computer toUpdate){
		computerDAO.update(toUpdate);
	}
	
	public void deleteComputer(int id){
		computerDAO.delete(id);
	}
}