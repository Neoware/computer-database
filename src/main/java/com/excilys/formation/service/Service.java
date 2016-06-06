package com.excilys.formation.service;

/**
 * Service layer interface.
 * 
 * @author neoware
 *
 * @param <T>
 *            The type of the entity that will be managed by this service.
 */
public interface Service<T> {

	Page<T> getPage(PageRequest pageRequest);

	int count();
}
