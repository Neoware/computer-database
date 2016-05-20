package com.excilys.formation.command;

import com.excilys.formation.service.CompanyServiceOLD;
import com.excilys.formation.service.ComputerServiceold;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Command {

	static final Logger LOG = LoggerFactory.getLogger(Command.class);
	private CompanyServiceOLD companyService;
	private ComputerServiceold computerService;

	public Command() {
		companyService = new CompanyServiceOLD();
		computerService = new ComputerServiceold();
	}

	public void Dispatch(String command){
		switch (command){
		case "list-computers":
			LOG.info("Launching list-computers command");
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
