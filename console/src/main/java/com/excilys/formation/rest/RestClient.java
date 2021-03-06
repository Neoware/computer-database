package com.excilys.formation.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.Page;

public class RestClient {

	private static final String API_BASE_URL = "http://localhost:8080/cdb-webapp/api";

	public String createComputer(ComputerDTO computerDTO) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/computer/create");
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN);
		Response response = invocationBuilder.post(Entity.entity(computerDTO, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}

	public ComputerDTO getComputer(long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/computer/" + id);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ComputerDTO computer = response.readEntity(ComputerDTO.class);
		return computer;
	}

	public String deleteComputer(long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/computer/delete/" + id);
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN);
		Response response = invocationBuilder.delete();
		return response.readEntity(String.class);
	}

	public String updateComputer(ComputerDTO computerDTO) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/computer/update");
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN);
		Response response = invocationBuilder.put(Entity.entity(computerDTO, MediaType.APPLICATION_JSON));
		return response.readEntity(String.class);
	}

	public String deleteCompany(long id) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/company/delete/" + id);
		Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_PLAIN);
		Response response = invocationBuilder.delete();
		return response.readEntity(String.class);
	}

	public Page<ComputerDTO> getComputerPage(int page, int pagesize) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/computer/list/" + page + "/" + pagesize);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		return response.readEntity(new GenericType<Page<ComputerDTO>>() {
		});
	}

	public Page<CompanyDTO> getCompanyPage(int page, int pagesize) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(API_BASE_URL).path("/company/list/" + page + "/" + pagesize);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		return response.readEntity(new GenericType<Page<CompanyDTO>>() {
		});
	}
}
