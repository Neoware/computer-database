package com.excilys.formation.persistence;

import java.util.List;

public interface DAO<T> {
	
	/**
	 * 
	 * @param id id of the element we want to retrieve
	 * @return the element if it has been found or null otherwise
	 */
	public abstract T find(int id);
	
	/**
	 * 
	 * @param toCreate the element that need to be created
	 * @return the created element
	 */
	public abstract T create (T toCreate);
	
	/**
	 * 
	 * @param toUpdate the element that need to be updated
	 * @return the updated element
	 */
	public abstract T update (T toUpdate);
	
	/**
	 * 
	 * @param toDelete the element that need to be deleted
	 * @return The deleted element
	 */
	public abstract T delete (int id);
	
	/**
	 * 
	 * @return the list of all elements with all informations
	 */
	
	public abstract List <T> getAll();
	
}
