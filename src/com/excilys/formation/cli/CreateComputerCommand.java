package com.excilys.formation.cli;

import java.sql.Timestamp;
import java.util.Scanner;

import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.utils.DateUtils;

public class CreateComputerCommand implements Command {
	private static ComputerService computerService;
	private static CompanyService companyService;
	
	public CreateComputerCommand() {
		computerService = ComputerService.getInstance();
		companyService = CompanyService.getInstance();
	}

	@Override
	public boolean execute() {
		Scanner scanner = new Scanner(System.in);
		String name;
		do {
			System.out.println("Choose a name for your computer (mandatory)");
			name = scanner.nextLine();
			System.out.println("You have pressed enter");
		} while (name.equals(""));
		Computer.ComputerBuilder builder = new Computer.ComputerBuilder(name);
		System.out.println("Choose a timestamp introduced for your computer (not mandatory)");
		String introduced = scanner.nextLine();
		Timestamp introducedTimestamp = DateUtils.getTimestampFromString(introduced);
		if (introducedTimestamp == null)
			System.out.println("Bad timestamp format setting to default");
		else
			builder.introduced(DateUtils.getLocalDateFromTimestamp(introducedTimestamp));
		System.out.println("Choose a timestamp discontinued for your computer (not mandatory)");
		String discontinued = scanner.nextLine();
		Timestamp discontinuedTimestamp = DateUtils.getTimestampFromString(discontinued);
		if (discontinuedTimestamp == null)
			System.out.println("Bad timestamp format setting to default");
		else
			builder.discontinued(DateUtils.getLocalDateFromTimestamp(introducedTimestamp));
		System.out.println("Choose the id of the manufacturer of the computer");
		if (scanner.hasNextLong()) {
			Long companyId = scanner.nextLong();
			Company company  = companyService.getById(companyId);
			if (company != null) {
				builder.computerCompany(company);
			} else {
				System.out.println("No company or non existing one, fixing to default");
			}
			Computer computer = builder.build();
			computerService.create(computer);
		}
		return true;
	}

}
