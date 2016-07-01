package com.excilys.formation.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.excilys.formation.entity.Company;

/**
 * Command to list companies with a pagination system.
 * 
 * @author neoware
 *
 */
@Component
public class ListCompaniesCommand implements Command {

	public ListCompaniesCommand() {
	}

	@Override
	public boolean execute() {
		List<Company> companies = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		String navigation;
		boolean refresh = true;
		while (shouldContinue == true) {
			if (refresh == true) {
				for (Company company : companies) {
					System.out.println(company);
				}
			}
			System.out.println("n for next page, p for previous, a to abort and go back to shell");
			if (scanner.hasNextLine()) {
				refresh = false;
			}
			navigation = scanner.nextLine();
			if (navigation.equals("n")) {
				if (pages.next() == true) {
					companies = pages.getCurrentPageElements();
					refresh = true;
				}
			} else if (navigation.equals("p")) {
				if (pages.previous() == true) {
					companies = pages.getCurrentPageElements();
					refresh = true;
				}
			} else if (navigation.equals("a")) {
				shouldContinue = false;
			}
		}
		return true;
	}

}
