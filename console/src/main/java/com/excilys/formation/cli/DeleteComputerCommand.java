package com.excilys.formation.cli;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	RestClient restClient;

	public DeleteComputerCommand() {
	}

	@Override
	public boolean execute(Scanner scanner) {
		System.out.print("What is the id of the computer you want to delete ?");
		if (scanner.hasNextLong()) {
			Long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println("Requesting delete for " + id + " ...");
			String result = restClient.deleteComputer(id);
			System.out.println(result);
		} else {
			System.out.println("An integer need to be submitted");
			scanner.nextLine();
		}
		return true;
	}

}
