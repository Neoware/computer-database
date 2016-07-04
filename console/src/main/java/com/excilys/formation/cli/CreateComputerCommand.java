package com.excilys.formation.cli;

import java.sql.Timestamp;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.excilys.formation.dto.ComputerDTO;
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
			System.out.println(computerDTO);
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:8080/cdb-webapp/api").path("/computer/create");
			Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN);
			Response response = invocationBuilder.post(Entity.entity(computerDTO, MediaType.APPLICATION_JSON));
			System.out.println(response.readEntity(String.class));
		}
		return true;
	}

}
