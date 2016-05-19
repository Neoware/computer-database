package com.excilys.formation.service;

/**
 * 
 * @author neoware
 * Provide several methods to manipulate or get informations on the computers
 *
 */
public interface ComputerService {

	/**
	 * List all computers with all data
	 */
	public void listAllComputers();
	
	/**
	 * Show all information about a specific computer
	 */
	public void showComputerDetails();
	
	/**
	 * To create a computer by specifying all data
	 */
	public void createComputer();
	
	/**
	 * To update an already existing computer
	 */
	public void updateComputer();
	
	/**
	 * To delete a computer if it exists
	 */
	public void deleteComputer();
}
