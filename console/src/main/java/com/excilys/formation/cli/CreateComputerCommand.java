package com.excilys.formation.cli;

import java.sql.Timestamp;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.rest.RestClient;
import com.excilys.formation.util.DateUtils;

/**
 * Command to create a computer by precising the mandatory attribute name, then
 * some optional attributes if desired
 * 
 * @author neoware
 *
 */
@Component
public class CreateComputerCommand implements Command {

	@Autowired
	RestClient restClient;

	public CreateComputerCommand() {
	}

	@Override
	public boolean execute(Scanner scanner) {
		String name;
		do {
			System.out.println("Choose a name for your computer (mandatory)");
			name = scanner.nextLine();
			System.out.println("You have pressed enter");
		} while (name.equals(""));
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setName(name);
		System.out.println("Choose a timestamp introduced for your computer (not mandatory)");
		String introduced = scanner.nextLine();
		Timestamp introducedTimestamp = DateUtils.getTimestampFromString(introduced);
		if (introducedTimestamp == null) {
			System.out.println("Bad timestamp format setting to default");
		} else {
			computerDTO.setIntroduced(introducedTimestamp.toString());
		}
		System.out.println("Choose a timestamp discontinued for your computer (not mandatory)");
		String discontinued = scanner.nextLine();
		Timestamp discontinuedTimestamp = DateUtils.getTimestampFromString(discontinued);
		if (discontinuedTimestamp == null) {
			System.out.println("Bad timestamp format setting to default");
		} else {
			computerDTO.setDiscontinued(introducedTimestamp.toString());
		}
		System.out.println("Choose the id of the manufacturer of the computer");
		if (scanner.hasNextLong()) {
			Long companyId = scanner.nextLong();
			computerDTO.setCompanyId(companyId.toString());
			System.out.println("Requesting creation...");
			String result = restClient.createComputer(computerDTO);
			System.out.println(result);
		}
		return true;
	}

}
