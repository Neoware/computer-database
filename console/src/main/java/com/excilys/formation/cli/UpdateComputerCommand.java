package com.excilys.formation.cli;

import java.sql.Timestamp;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.rest.RestClient;
import com.excilys.formation.util.DateUtils;

/**
 * Command to update an already existing computer by submitting one or multiple
 * attributes that will be modified in database.
 * 
 * @author neoware
 *
 */
@Component
public class UpdateComputerCommand implements Command {

	public UpdateComputerCommand() {
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
		ComputerDTO toUpdate = new ComputerDTO();
		toUpdate.setId(id.toString());
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
					toUpdate.setIntroduced(introducedTimestamp.toString());
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
					toUpdate.setDiscontinued(discontinuedTimestamp.toString());
				} else {
					System.out.println("Bad timestamp");
				}
			}
			System.out.println("Change the company id of the computer ? (current: " + toUpdate.getCompanyId() + ")");
			if (scanner.hasNextLong()) {
				Long companyId = scanner.nextLong();
				System.out.println(companyId);
				toUpdate.setCompanyId(companyId.toString());
			}
			String result = RestClient.updateComputer(toUpdate);
			System.out.println(result);
		}
		return true;
	}

}
