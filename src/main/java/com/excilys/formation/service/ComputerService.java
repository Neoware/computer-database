package com.excilys.formation.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.util.ComputerMapper;
import com.excilys.formation.util.StringUtils;

/**
 * Service class for the Computer entity. Is a singleton.
 * @author Neoware
 *
 */
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

	/**
	 * Function to access the list of all computers
	 * @return the list containing all computers
	 */
	public List<Computer> getAll() {
		ConnectionThreadLocal.getInstance().initConnection();
		List<Computer> computers = computerDAO.getAll();
		ConnectionThreadLocal.getInstance().close();
		return computers;
	}

	/**
	 * Function used to get specific part of the computers
	 * @param pageRequest The wrapper of the request that was extract from the servlet
	 * @return a page of computer containing elements and metadata for view purpose
	 */
	@Override
	public Page<ComputerDTO> getPage(PageRequest pageRequest) {
		ConnectionThreadLocal.getInstance().initConnection();
		ConnectionThreadLocal.getInstance().beginTransaction();
		List<Computer> computerList = computerDAO.getPage(pageRequest);
		int count;
		if (StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
			count = computerDAO.count();
		} else {
			count = computerDAO.getCountElements(pageRequest);
		}
		ConnectionThreadLocal.getInstance().commit();
		ConnectionThreadLocal.getInstance().endTransaction();
		List<ComputerDTO> computerDtos = ComputerMapper.fromEntitiesToDtos(computerList);
		Page<ComputerDTO> computerPage = new Page<>(computerDtos, pageRequest, count);
		ConnectionThreadLocal.getInstance().close();
		return computerPage;
	}

	/**
	 * Function to retrieve a computer by id
	 * 
	 * @param id
	 *            the id of the computer that we are looking for
	 * @return the computer is found, null otherwise
	 */
	public Computer getById(Long id) {
		ConnectionThreadLocal.getInstance().initConnection();
		Computer computer = computerDAO.find(id);
		ConnectionThreadLocal.getInstance().close();
		return computer;
	}

	/**
	 * Function to create a new computer
	 * 
	 * @param toCreate
	 *            the computer that is going to be created
	 * @return toCreate with the id set is the add is successful
	 */
	public Computer create(Computer toCreate) {
		ConnectionThreadLocal.getInstance().initConnection();
		computerDAO.create(toCreate);
		ConnectionThreadLocal.getInstance().close();
		return toCreate;
	}

	/**
	 * Function to update an already existing computer
	 * 
	 * @param toUpdate
	 *            the computer with the right id that will be updated
	 * @return the updated computer //TODO useless
	 */
	public Computer update(Computer toUpdate) {
		ConnectionThreadLocal.getInstance().initConnection();
		computerDAO.update(toUpdate);
		ConnectionThreadLocal.getInstance().close();
		return toUpdate;
	}

	/**
	 * Delete a computer by id
	 * 
	 * @param id
	 *            the id of the computer that is going to be deleted
	 */
	public void delete(Long id) {
		ConnectionThreadLocal.getInstance().initConnection();
		computerDAO.delete(id);
		ConnectionThreadLocal.getInstance().close();
	}

	/**
	 * Function allowing to delete multiple computers by specifying their ids
	 * 
	 * @param lists
	 *            This is the list of computers id that the function is going to
	 *            delete one by one
	 * 
	 */
	public void deleteList(String[] lists) {
		ConnectionThreadLocal.getInstance().initConnection();
		for (int i = 0; i < lists.length; i++) {
			computerDAO.delete(Long.parseLong(lists[i]));
		}
		ConnectionThreadLocal.getInstance().close();
	}

	/**
	 * Function used to retrieve the number of computers actually in the
	 * database
	 * 
	 * @return count the number of computers in database
	 */
	@Override
	public int count() {
		ConnectionThreadLocal.getInstance().initConnection();
		int result = computerDAO.count();
		ConnectionThreadLocal.getInstance().close();
		return result;
	}
}
