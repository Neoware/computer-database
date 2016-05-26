package com.excilys.formation.cli;

import java.util.Scanner;

import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.ComputerService;

public class DeleteComputerCommand implements Command {

	private static ComputerService computerService;

	public DeleteComputerCommand() {
		computerService = ComputerService.getInstance();
	}

	@Override
	public boolean execute() {

		Scanner scanner = new Scanner(System.in);
		System.out.print("What is the id of the computer you want to delete ?");
		if (scanner.hasNextLong()) {
			Long id = scanner.nextLong();
			scanner.nextLine();
			Computer temp = computerService.getById(id);
			if (temp != null) {
				System.out.println("Deleting " + temp + " ...");
				computerService.delete(temp.getId());
				System.out.println("Success");
			} else {
				System.out.println("Computer with this id doesn\'t exist");
			}
		} else {
			System.out.println("An integer need to be submitted");
			scanner.nextLine();
		}
		return true;
	}

}
