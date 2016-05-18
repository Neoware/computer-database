package com.excilys.formation.service;

import java.util.List;
import java.util.Scanner;



import com.excilys.formation.model.Page;
import com.excilys.formation.persistence.Company;
import com.excilys.formation.persistence.CompanyDAO;

public class CompanyServiceCLI implements CompanyService{

	private Scanner scanner;
	private CompanyDAO companyDAO;

	public CompanyServiceCLI(){
		companyDAO = new CompanyDAO();
		scanner = new Scanner(System.in);
	}

	public void listAllCompanies(){
		List <Company> companies = companyDAO.getAll();
		Page <Company> pages = new Page<Company>(companies);

		String navigation;
		while (true){
			pages.printPage();
			System.out.println("n for next page, p for previous, a to abort and go back to shell");
			if (scanner.hasNextLine())
			{
				navigation = scanner.nextLine();
				if (navigation.equals("n"))
					pages.next();
				else if (navigation.equals("p"))
					pages.previous();
				else if (navigation.equals("a"))
					return;
			}
		}
	}
}
