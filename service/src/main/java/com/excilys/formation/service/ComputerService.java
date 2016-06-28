package com.excilys.formation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.exception.DaoException;
import com.excilys.formation.mapper.ComputerMapper;
import com.excilys.formation.persistence.Cache;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.persistence.PageRequest;
import com.excilys.formation.util.StringUtils;

/**
 * Service class for the Computer entity. Is a singleton.
 * 
 * @author Neoware
 *
 */
@Service
@Transactional(rollbackFor = DaoException.class, propagation = Propagation.REQUIRES_NEW)
public class ComputerService {

	private static final Logger LOG = LoggerFactory.getLogger(ComputerService.class);
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private Cache cache;

	public ComputerService() {
	}

	/**
	 * Function used to get specific part of the computers
	 * 
	 * @param pageRequest
	 *            The wrapper of the request that was extract from the servlet
	 * @return a page of computer containing elements and metadata for view
	 *         purpose
	 */
	public Page<ComputerDTO> getPage(PageRequest pageRequest) {
		List<Computer> computerList = computerDAO.getPage(pageRequest);
		int count;
		if (StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
			count = computerDAO.count();
		} else {
			count = computerDAO.getCountElements(pageRequest);
		}
		LOG.info("" + count);
		List<ComputerDTO> computerDtos = ComputerMapper.fromEntitiesToDtos(computerList);
		Page<ComputerDTO> computerPage = new Page<>(computerDtos, pageRequest, count);
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
		Computer computer = computerDAO.find(id);
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
		computerDAO.create(toCreate);
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
		computerDAO.update(toUpdate);
		return toUpdate;
	}

	/**
	 * Delete a computer by id
	 * 
	 * @param id
	 *            the id of the computer that is going to be deleted
	 */
	public void delete(Long id) {
		computerDAO.delete(id);
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
		for (int i = 0; i < lists.length; i++) {
			computerDAO.delete(Long.parseLong(lists[i]));
		}
	}

	/**
	 * Function used to retrieve the number of computers actually in the
	 * database
	 * 
	 * @return count the number of computers in database
	 */
	public int count() {
		int result = computerDAO.count();
		if (cache.getCount() == null) {
			cache.setCount(result);
		}
		return result;
	}
}
