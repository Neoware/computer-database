package com.excilys.formation.dispatcher;

import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.CompanyServiceCLI;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.service.ComputerServiceCLI;


public class CommandDispatcher {

	private CompanyService companyService;
	private ComputerService computerService;

	public CommandDispatcher() {
		companyService = new CompanyServiceCLI();
		computerService = new ComputerServiceCLI();
	}

	public void Dispatch(String command){
		switch (command){
		case "list-computers":
			computerService.listAllComputers();
			break;
		case "list-companies":
			companyService.listAllCompanies();
			break;
		case "computer":
			computerService.showComputerDetails();
			break;
		case "create":
			computerService.createComputer();
			break;
		case "update":
			computerService.updateComputer();
			break;
		case "delete":
			computerService.deleteComputer();
			break;
		case "exit":
			System.out.println("Exiting...");
			System.exit(0);
		case "":
			break;
		default:
			System.out.println("No such command");
		}
	}

}
