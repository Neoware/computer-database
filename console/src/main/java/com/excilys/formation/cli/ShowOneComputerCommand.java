package com.excilys.formation.cli;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.springframework.stereotype.Component;

/**
 * Command to show information about one computer by providing its id.
 * 
 * @author neoware
 *
 */
@Component
public class ShowOneComputerCommand implements Command {

	public ShowOneComputerCommand() {
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
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:").path("resource");
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
