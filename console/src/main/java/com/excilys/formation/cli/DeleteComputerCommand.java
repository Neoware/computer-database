package com.excilys.formation.cli;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.excilys.formation.rest.RestClient;

/**
 * Command to delete a computer by giving its id.
 * 
 * @author neoware
 *
 */
@Component
public class DeleteComputerCommand implements Command {

	public DeleteComputerCommand() {
	}

	@Override
	public boolean execute() {

		Scanner scanner = new Scanner(System.in);
		System.out.print("What is the id of the computer you want to delete ?");
		if (scanner.hasNextLong()) {
			Long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println("Requesting delete for " + id + " ...");
			String result = RestClient.deleteComputer(id);
			System.out.println(result);
		} else {
			System.out.println("An integer need to be submitted");
			scanner.nextLine();
		}
		return true;
	}

}
