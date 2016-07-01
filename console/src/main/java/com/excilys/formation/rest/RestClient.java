package com.excilys.formation.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.excilys.formation.dto.ComputerDTO;

public class RestClient {

	private static final String API_BASE_URL = "localhost:8080/cdb-webapp/api";

	public static ComputerDTO getComputer(long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/computer/" + id);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ComputerDTO computer = response.readEntity(ComputerDTO.class);
		return computer;
	}

	public static String deleteComputer(long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/computer/delete/" + id);
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN);
		Response response = invocationBuilder.delete();
		return response.readEntity(String.class);
	}

	public static String updateComputer(ComputerDTO computerDTO) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/computer/update");
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN);
		Response response = invocationBuilder.put(Entity.entity(computerDTO, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}

	public static String deleteCompany(long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/company/delete/" + id);
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN);
		Response response = invocationBuilder.delete();
		return response.readEntity(String.class);
	}
}
