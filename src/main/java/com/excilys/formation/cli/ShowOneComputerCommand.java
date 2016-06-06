package com.excilys.formation.cli;

import java.util.Scanner;

import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.ComputerService;

/**
 * Command to show information about one computer by providing its id.
 * 
 * @author neoware
 *
 */
public class ShowOneComputerCommand implements Command {

	private static ComputerService computerService;

	public ShowOneComputerCommand() {
		computerService = ComputerService.getInstance();
	}

	@Override
	public boolean execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is the id of the computer you want details about ?");
		System.out.print("> ");
		Boolean finished = false;
		while (finished == false) {
			while (!scanner.hasNextLong()) {
				System.out.println("You need to provide a correct id");
				scanner.nextLine();
				System.out.print("> ");
			}
			Long id = scanner.nextLong();
			Computer temp = computerService.getById(id);
			if (temp != null) {
				System.out.println(temp);
				finished = true;
			} else {
				System.out.println("Computer with this id doesn\'t exist");
				scanner.nextLine();
				System.out.print("> ");
			}
		}
		return true;
	}

}
