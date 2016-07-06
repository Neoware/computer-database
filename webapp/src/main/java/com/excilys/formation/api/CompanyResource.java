package com.excilys.formation.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.persistence.PageRequest;
import com.excilys.formation.service.Page;
import com.excilys.formation.service.interfaces.CompanyService;

@Path("company")
@Component
public class CompanyResource {
	@Autowired
	CompanyService companyService;

	@GET
	@Path("/list/{page}/{pageSize}")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<CompanyDTO> listcompanies(@PathParam("page") int page, @PathParam("pageSize") int pageSize) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.setLimit(pageSize);
		pageRequest.setPage(page);
		pageRequest.init();
		Page<CompanyDTO> companyPage = companyService.getPage(pageRequest);
		return companyPage;
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCompany(@PathParam("id") long id) {
		companyService.delete(id);
		return "success";
	}

}
