package com.excilys.formation.api;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.mapper.ComputerMapper;
import com.excilys.formation.persistence.PageRequest;
import com.excilys.formation.service.Page;
import com.excilys.formation.service.interfaces.ComputerService;

@Path("/computer")
@Component
public class ComputerResource {
	@Autowired
	private ComputerService computerService;

	@GET
	public String Test() {
		return "ok";
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO ShowOneComputer(@PathParam("id") long id) {
		Computer computer = computerService.getById(id);
		ComputerDTO computerDTO = ComputerMapper.fromEntityToDto(computer);
		return computerDTO;
	}

	@GET
	@Path("/list/{page}/{pageSize}")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ComputerDTO> listComputer(@PathParam("page") int page, @PathParam("pageSize") int pageSize) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPage(page);
		pageRequest.setLimit(pageSize);
		pageRequest.init();
		Page<ComputerDTO> computerPage = computerService.getPage(pageRequest);
		return computerPage;
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addComputer(@Valid ComputerDTO computerDTO) {
		Computer computer = ComputerMapper.fromDtoToEntity(computerDTO);
		computerService.create(computer);
		return "success";
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateComputer(@Valid ComputerDTO computerDTO) {
		Computer computer = ComputerMapper.fromDtoToEntity(computerDTO);
		computerService.update(computer);
		return "success";
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteComputer(@PathParam("id") long id) {
		computerService.delete(id);
		return "success";
	}
}
