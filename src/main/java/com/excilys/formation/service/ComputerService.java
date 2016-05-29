package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.utils.ComputerMapper;

public class ComputerService implements Service<Computer> {

	private static ComputerDAO computerDAO;
	private static ComputerService instance;

	private ComputerService() {
		computerDAO = ComputerDAO.getInstance();
	}

	public static ComputerService getInstance() {
		if (instance == null) {
			synchronized (ComputerService.class) {
				if (instance == null) {
					instance = new ComputerService();
				}
			}
		}
		return instance;
	}

	public List<Computer> getAll() {
		List<Computer> computers = computerDAO.getAll();

		return computers;
	}

	@Override
	public List<Computer> getPage(PageRequest pageRequest) {
		int offset = pageRequest.getLimit() * pageRequest.getPage();
		List <Computer> computers = computerDAO.getLimited(offset, pageRequest.getLimit());
		return computers;
	}

	public Computer getById(Long id) {
		Computer computer = computerDAO.find(id);
		return computer;
	}

	public Computer create(Computer toCreate) {
		computerDAO.create(toCreate);
		return toCreate;
	}

	public Computer update(Computer toUpdate) {
		computerDAO.update(toUpdate);
		return toUpdate;
	}

	public void delete(Long id) {
		computerDAO.delete(id);
	}

	@Override
	public int count() {
		return computerDAO.count();
	}

}
