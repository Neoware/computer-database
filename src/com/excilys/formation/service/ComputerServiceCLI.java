package com.excilys.formation.service;

import java.util.List;
import java.util.Scanner;

import com.excilys.formation.persistence.Computer;
import com.excilys.formation.persistence.ComputerDAO;

public class ComputerServiceCLI implements ComputerService{

	private ComputerDAO computerDAO;
	private Scanner scanner;

	public ComputerServiceCLI(){
		computerDAO = new ComputerDAO();
		scanner = new Scanner(System.in);
	}

	public void listAllComputers(){
		List <Computer> computers = computerDAO.getAll();
		for (Computer computer : computers){
			System.out.println(computer);
		}
	}

	public void showComputerDetails(){
		System.out.print("What is the id of the computer you want details about ?");
		if (scanner.hasNextInt())
		{
			int id = scanner.nextInt();
			Computer temp = computerDAO.find(id);
			if (temp != null)
				System.out.println(temp);
			else
				System.out.println("Computer with this id doesn\'t exist");
		}
		else
			System.out.println("An integer need to be submitted");
	}

	public void createComputer(){
		Computer toCreate = new Computer();
		computerDAO.create(toCreate);
	}

	public void updateComputer(){
		Computer toUpdate = new Computer();
		computerDAO.update(toUpdate);
	}

	public void deleteComputer(){
		System.out.print("What is the id of the computer you want to delete ?");
		if (scanner.hasNextInt())
		{
			int id = scanner.nextInt();
			Computer temp = computerDAO.find(id);
			if (temp != null)
			{
				System.out.println("Deleting " + temp + " ...");
				computerDAO.delete(temp.getId());
				System.out.println("Success");
			}
			else
				System.out.println("Computer with this id doesn\'t exist");
		}
		else
			System.out.println("An integer need to be submitted");
	}
}