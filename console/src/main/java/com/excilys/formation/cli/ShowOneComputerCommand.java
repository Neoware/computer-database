package com.excilys.formation.cli;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.rest.RestClient;

/**
 * Command to show information about one computer by providing its id.
 * 
 * @author neoware
 *
 */
@Component
public class ShowOneComputerCommand implements Command {

	@Autowired
	RestClient restClient;

	public ShowOneComputerCommand() {
	}

	@Override
	public boolean execute(Scanner scanner) {
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
			ComputerDTO computer = restClient.getComputer(id);
			if (computer != null) {
				System.out.println(computer);
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
