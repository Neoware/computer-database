package com.excilys.formation.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.service.ComputerService;

/**
 * Command to list all computers with a pagination system.
 * 
 * @author neoware
 *
 */
@Component
public class ListComputersCommand implements Command {
	@Autowired
	private ComputerService computerService;

	public ListComputersCommand() {
	}

	@Override
	public boolean execute() {
		// List<Computer> computers = new ArrayList<>();
		// Page<Computer> pages = new Page<Computer>(computerService);
		// computers = pages.getCurrentPageElements();
		// Scanner scanner = new Scanner(System.in);
		// String navigation;
		// boolean shouldContinue = true;
		// boolean refresh = true;
		// while (shouldContinue == true) {
		// if (refresh == true) {
		// for (Computer computer : computers) {
		// System.out.println(computer);
		// }
		// }
		// System.out.println("n for next page, p for previous, a to abort and
		// go back to shell");
		// navigation = scanner.nextLine();
		// if (navigation.equals("n")) {
		// if (pages.next() == true) {
		// computers = pages.getCurrentPageElements();
		// refresh = true;
		// } else {
		// refresh = false;
		// }
		// } else if (navigation.equals("p")) {
		// if (pages.previous() == true) {
		// computers = pages.getCurrentPageElements();
		// refresh = true;
		// } else {
		// refresh = false;
		// }
		// } else if (navigation.equals("a")) {
		// shouldContinue = false;
		// }
		// }
		// scanner.close();
		return true;
	}

}
