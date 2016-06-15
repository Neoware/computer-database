package com.excilys.formation.cli;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.entity.Company;
import com.excilys.formation.service.CompanyService;

/**
 * Command to delete a company by submitting its id. All computers that have
 * this company_id will be deleted too.
 * 
 * @author neoware
 *
 */
@Component
public class DeleteCompanyCommand implements Command {
	@Autowired
	private CompanyService companyService;

	public DeleteCompanyCommand() {
	}

	@Override
	public boolean execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("What is the id of the company you want to delete ?");
		if (scanner.hasNextLong()) {
			Long id = scanner.nextLong();
			scanner.nextLine();
			Company temp = companyService.getById(id);
			if (temp != null) {
				System.out.println("Deleting " + temp + " ...");
				companyService.delete(temp.getId());
				System.out.println("Success");
			} else {
				System.out.println("Company with this id doesn\'t exist");
			}
		} else {
			System.out.println("An integer need to be submitted");
			scanner.nextLine();
		}
		return true;
	}

}
