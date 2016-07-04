package com.excilys.formation.cli;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.rest.RestClient;
import com.excilys.formation.service.Page;

/**
 * Command to list all computers with a pagination system.
 * 
 * @author neoware
 *
 */
@Component
public class ListComputersCommand implements Command {

	public ListComputersCommand() {
	}

	@Override
	public boolean execute(Scanner scanner) {
		boolean shouldContinue = true;
		String navigation;
		int page = 1;
		int pageSize = 20;
		boolean refresh = true;
		Page<ComputerDTO> computers = null;
		while (shouldContinue == true) {
			if (refresh == true) {
				computers = RestClient.getComputerPage(page, pageSize);
				for (ComputerDTO company : computers.getCurrentPageElements()) {
					System.out.println(company);
				}
				System.out.println("n for next page, p for previous, a to abort and go back to shell");
			}
			if (scanner.hasNextLine()) {
				refresh = false;
			}
			navigation = scanner.nextLine();
			if (navigation.equals("n")) {
				if (page < computers.getTotalPage()) {
					page++;
					refresh = true;
				}
			} else if (navigation.equals("p")) {
				if (page > 1) {
					page--;
					refresh = true;
				}
			} else if (navigation.equals("a")) {
				shouldContinue = false;
			}
		}
		return true;
	}

}
