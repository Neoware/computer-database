package com.excilys.formation.service.interfaces;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.persistence.PageRequest;
import com.excilys.formation.service.Page;

public interface ComputerService {

	/**
	 * Function used to get specific part of the computers
	 * 
	 * @param pageRequest
	 *            The wrapper of the request that was extract from the servlet
	 * @return a page of computer containing elements and metadata for view
	 *         purpose
	 */
	Page<ComputerDTO> getPage(PageRequest pageRequest);

	/**
	 * Function to retrieve a computer by id
	 * 
	 * @param id
	 *            the id of the computer that we are looking for
	 * @return the computer is found, null otherwise
	 */
	Computer getById(Long id);

	/**
	 * Function to create a new computer
	 * 
	 * @param toCreate
	 *            the computer that is going to be created
	 * @return toCreate with the id set is the add is successful
	 */
	Computer create(Computer toCreate);

	/**
	 * Function to update an already existing computer
	 * 
	 * @param toUpdate
	 *            the computer with the right id that will be updated
	 * @return the updated computer //TODO useless
	 */
	Computer update(Computer toUpdate);

	/**
	 * Delete a computer by id
	 * 
	 * @param id
	 *            the id of the computer that is going to be deleted
	 */
	void delete(Long id);

	/**
	 * Function allowing to delete multiple computers by specifying their ids
	 * 
	 * @param lists
	 *            This is the list of computers id that the function is going to
	 *            delete one by one
	 * 
	 */
	void deleteList(String[] lists);

	/**
	 * Function used to retrieve the number of computers actually in the
	 * database
	 * 
	 * @return count the number of computers in database
	 */
	int count();

}