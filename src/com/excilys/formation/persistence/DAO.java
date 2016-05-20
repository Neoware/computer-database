package com.excilys.formation.persistence;

import java.util.List;

public interface DAO<T> {
	
	/**
	 * 
	 * @param id id of the element we want to retrieve
	 * @return the element if it has been found or null otherwise
	 */
	public T find(Long id);
	
	/**
	 * 
	 * @param toCreate the element that need to be created
	 * @return the created element
	 */
	public T create (T toCreate);
	
	/**
	 * 
	 * @param toUpdate the element that need to be updated
	 * @return the updated element
	 */
	public T update (T toUpdate);
	
	/**
	 * 
	 * @param toDelete the element that need to be deleted
	 * @return The deleted element
	 */
	public T delete (Long id);
	
	/**
	 * 
	 * @return the list of all elements with all informations
	 */
	
	public List <T> getAll();
	
}
