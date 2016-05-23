package com.excilys.formation.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.command.Page;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.ComputerService;

public class ListComputersCommand implements Command {
	private static ComputerService computerService;

	public ListComputersCommand() {
		computerService = ComputerService.getInstance();
	}

	@Override
	public boolean execute() {
		List<Computer> computers = new ArrayList<>();
		Page<Computer> pages = new Page<Computer>(computerService);
		computers = pages.getCurrentPageElements();
		Scanner scanner = new Scanner(System.in);
		String navigation;
		boolean shouldContinue = true;
		boolean refresh = true;
		while (shouldContinue == true) {
			if (refresh == true) {
				for (Computer computer : computers) {
					System.out.println(computer);
				}
			}
			System.out.println("n for next page, p for previous, a to abort and go back to shell");
			navigation = scanner.nextLine();
			if (navigation.equals("n")) {
				if (pages.next() == true) {
					computers = pages.getCurrentPageElements();
					refresh = true;
				} else
					refresh = false;
			} else if (navigation.equals("p")) {
				if (pages.previous() == true) {
					computers = pages.getCurrentPageElements();
					refresh = true;
				} else
					refresh = false;
			} else if (navigation.equals("a"))
				shouldContinue = false;
		}
		//scanner.close();
		return true;
	}

}
