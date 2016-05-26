package com.excilys.formation.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.command.Page;
import com.excilys.formation.entity.Company;
import com.excilys.formation.service.CompanyService;

public class ListCompaniesCommand implements Command {

	CompanyService companyService;

	public ListCompaniesCommand() {
		companyService = CompanyService.getInstance();
	}

	@Override
	public boolean execute() {
		List<Company> companies = new ArrayList<>();
		Page<Company> pages = new Page<Company>(companyService);
		companies = pages.getCurrentPageElements();
		Scanner scanner = new Scanner(System.in);
		String navigation;
		boolean shouldContinue = true;
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
