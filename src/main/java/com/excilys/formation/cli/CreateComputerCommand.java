package com.excilys.formation.cli;

import java.sql.Timestamp;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.util.DateUtils;

/**
 * Command to create a computer by precising the mandatory attribute name, then
 * some optional attributes if desired
 * 
 * @author neoware
 *
 */
@Component("createComputerCommand")
public class CreateComputerCommand implements Command {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	public CreateComputerCommand() {
		System.out.println("create constructor");
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
		Computer.Builder builder = Computer.getBuilder();
		System.out.println("Choose a timestamp introduced for your computer (not mandatory)");
		String introduced = scanner.nextLine();
		Timestamp introducedTimestamp = DateUtils.getTimestampFromString(introduced);
		if (introducedTimestamp == null) {
			System.out.println("Bad timestamp format setting to default");
		} else {
			builder.introduced(DateUtils.getLocalDateFromTimestamp(introducedTimestamp));
		}
		System.out.println("Choose a timestamp discontinued for your computer (not mandatory)");
		String discontinued = scanner.nextLine();
		Timestamp discontinuedTimestamp = DateUtils.getTimestampFromString(discontinued);
		if (discontinuedTimestamp == null) {
			System.out.println("Bad timestamp format setting to default");
		} else {
			builder.discontinued(DateUtils.getLocalDateFromTimestamp(introducedTimestamp));
		}
		System.out.println("Choose the id of the manufacturer of the computer");
		if (scanner.hasNextLong()) {
			Long companyId = scanner.nextLong();
			// Company company =
			companyService.getById(companyId);
			// if (company != null) {
			// builder.computerCompany(company);
			// } else {
			// System.out.println("No company or non existing one, fixing to
			// default");
			// }
			// Computer computer = builder.build();
			// computerService.create(computer);
		}
		return true;
	}

}
