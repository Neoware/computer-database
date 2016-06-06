package com.excilys.formation.cli;

import java.sql.Timestamp;
import java.util.Scanner;

import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.util.DateUtils;

/**
 * Command to update an already existing computer by submitting one or multiple
 * attributes that will be modified in database.
 * 
 * @author neoware
 *
 */
public class UpdateComputerCommand implements Command {

	private static ComputerService computerService;
	private static CompanyService companyService;

	public UpdateComputerCommand() {
		computerService = ComputerService.getInstance();
		companyService = CompanyService.getInstance();
	}

	@Override
	public boolean execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("What is the id of the computer you want to update ?");
		while (!scanner.hasNextLong()) {
			if (scanner.next() != "") {
				System.out.println("You need to provide a correct id");
			}
			scanner.nextLine();
		}
		Long id = scanner.nextLong();
		scanner.nextLine();
		Computer toUpdate = computerService.getById(id);
		if (toUpdate != null) {
			System.out.println("Change the name of the computer ? (current: " + toUpdate.getName() + ")");
			if (scanner.hasNext()) {
				toUpdate.setName(scanner.nextLine());
			}
			System.out.println(
					"Change the introduced date of the computer ? (current: " + toUpdate.getIntroduced() + ")");
			if (scanner.hasNextLine()) {
				String introduced = scanner.nextLine();
				Timestamp introducedTimestamp = DateUtils.getTimestampFromString(introduced);
				if (introducedTimestamp != null) {
					toUpdate.setIntroduced(DateUtils.getLocalDateFromTimestamp(introducedTimestamp));
				} else {
					System.out.println("Bad timestamp");
				}
			}
			System.out.println(
					"Change the discontinued date of the computer ? (current: " + toUpdate.getDiscontinued() + ")");
			if (scanner.hasNextLine()) {
				String discontinued = scanner.nextLine();
				Timestamp discontinuedTimestamp = DateUtils.getTimestampFromString(discontinued);
				if (discontinuedTimestamp != null) {
					toUpdate.setDiscontinued(DateUtils.getLocalDateFromTimestamp(discontinuedTimestamp));
				} else {
					System.out.println("Bad timestamp");
				}
			}
			System.out.println(
					"Change the company id of the computer ? (current: " + toUpdate.getComputerCompany().getId() + ")");
			if (scanner.hasNextLong()) {
				Long companyId = scanner.nextLong();
				System.out.println(companyId);
				Company company = companyService.getById(companyId);
				if (company != null) {
					toUpdate.setComputerCompany(company);
				} else {
					System.out.println("No such company");
				}
			}
			computerService.update(toUpdate);
			System.out.println("Success");
		} else {
			System.out.println("Computer with this id doesn\'t exist");
		}
		return true;
	}

}
