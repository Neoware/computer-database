package com.excilys.formation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.util.ComputerMapper;
import com.excilys.formation.util.StringUtils;

public class ComputerService implements Service<ComputerDTO> {

	private static final Logger LOG = LoggerFactory.getLogger(ComputerService.class);
	private static ComputerDAO computerDAO;
	private static ComputerService instance = new ComputerService();

	private ComputerService() {
		computerDAO = ComputerDAO.getInstance();
	}

	public static ComputerService getInstance() {
		return instance;
	}

	public List<Computer> getAll() {
		ConnectionThreadLocal.getInstance().initConnection();
		List<Computer> computers = computerDAO.getAll();
		return computers;
	}

	@Override
	public Page<ComputerDTO> getPage(PageRequest pageRequest) {
		ConnectionThreadLocal.getInstance().initConnection();
		List<Computer> computerList = computerDAO.getPage(pageRequest);
		int count = 0;
		if (StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
			count = computerDAO.count();
		} else {
			count = computerDAO.getCountElements(pageRequest);
		}
		List<ComputerDTO> computerDtos = ComputerMapper.fromEntitiesToDtos(computerList);
		Page<ComputerDTO> computerPage = new Page<>(computerDtos, pageRequest, count);
		return computerPage;
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

	public void deleteList(String[] lists) {
		for (int i = 0; i < lists.length; i++) {
			computerDAO.delete(Long.parseLong(lists[i]));
		}
	}

	@Override
	public int count() {
		return computerDAO.count();
	}
}
