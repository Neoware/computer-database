package com.excilys.formation.cli;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.excilys.formation.rest.RestClient;

/**
 * Command to delete a company by submitting its id. All computers that have
 * this company_id will be deleted too.
 * 
 * @author neoware
 *
 */
@Component
public class DeleteCompanyCommand implements Command {

	public DeleteCompanyCommand() {
	}

	@Override
	public boolean execute(Scanner scanner) {
		System.out.print("What is the id of the company you want to delete ?");
		if (scanner.hasNextLong()) {
			Long id = scanner.nextLong();
			scanner.nextLine();
			System.out.println("Requesting delete for " + id + " ...");
			String result = RestClient.deleteCompany(id);
			System.out.println(result);
		} else {
			System.out.println("An integer need to be submitted");
			scanner.nextLine();
		}
		return true;
	}

}
