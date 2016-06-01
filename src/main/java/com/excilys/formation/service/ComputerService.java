package com.excilys.formation.service;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.persistence.ConnectionManager;
import com.excilys.formation.util.ComputerMapper;

public class ComputerService implements Service<ComputerDTO> {

	private static ComputerDAO computerDAO;
	private static ComputerService instance = new ComputerService();
	private static final Logger LOG = LoggerFactory.getLogger(ComputerService.class);
	private static ConnectionManager connectionManager;// trash

	private ComputerService() {
		computerDAO = ComputerDAO.getInstance();
		connectionManager = ConnectionManager.getInstance();
	}

	public static ComputerService getInstance() {
		return instance;
	}

	public List<Computer> getAll() {
		Connection connection = connectionManager.getConnection();
		List<Computer> computers = computerDAO.getAll(connection);

		return computers;
	}

	@Override
	public Page<ComputerDTO> getPage(PageRequest pageRequest) {
		Connection connection = connectionManager.getConnection();
		List<Computer> computerList = computerDAO.getPage(pageRequest, connection);
		int count = computerDAO.getCountElements(pageRequest, connection);
		List<ComputerDTO> computerDtos = ComputerMapper.fromEntitiesToDtos(computerList);
		Page<ComputerDTO> computerPage = new Page<>(computerDtos, pageRequest, count);
		return computerPage;
	}

	public Computer getById(Long id) {
		Connection connection = connectionManager.getConnection();
		Computer computer = computerDAO.find(id, connection);
		return computer;
	}

	public Computer create(Computer toCreate) {
		Connection connection = connectionManager.getConnection();
		computerDAO.create(toCreate, connection);
		return toCreate;
	}

	public Computer update(Computer toUpdate) {
		Connection connection = connectionManager.getConnection();
		computerDAO.update(toUpdate, connection);
		return toUpdate;
	}

	public void delete(Long id) {
		Connection connection = connectionManager.getConnection();
		computerDAO.delete(id, connection);
	}

	public void deleteList(String[] lists) {
		Connection connection = connectionManager.getConnection();
		for (int i = 0; i < lists.length; i++) {
			computerDAO.delete(Long.parseLong(lists[i]), connection);
		}
	}

	@Override
	public int count() {
		LOG.error("Entering count computer service");
		Connection connection = connectionManager.getConnection();
		return computerDAO.count(connection);
	}
}
