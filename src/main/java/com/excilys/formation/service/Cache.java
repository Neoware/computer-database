package com.excilys.formation.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.excilys.formation.entity.Company;

/**
 * Singleton repository for some data from the database in order to limit
 * database access.
 * 
 * @author Neoware
 *
 */

public class Cache {
	private static ConcurrentHashMap<String, Object> cacheRepository = new ConcurrentHashMap<>();

	/**
	 * Increment the count when a computer has been inserted.
	 */
	public void incrementCount() {
		if (cacheRepository.containsKey("count")) {
			cacheRepository.put("count", (Integer) cacheRepository.get("count") + 1);
		}
	}

	/**
	 * Decrement the count when a computer has been deleted.
	 */
	public void decrementCount() {
		if (cacheRepository.containsKey("count")) {
			cacheRepository.put("count", (Integer) cacheRepository.get("count") - 1);
		}
	}

	/**
	 * Add a company to the cache when a company has been inserted.
	 * 
	 * @param toAdd
	 *            The company that need to be added.
	 */
	@SuppressWarnings("unchecked")
	public void addCompany(Company toAdd) {
		if (cacheRepository.containsKey("companies")) {
			cacheRepository.put("companies", ((List<Company>) cacheRepository.get("companies")).add(toAdd));
		}
	}

	/**
	 * Remove a company in the cache when a company has been deleted.
	 * 
	 * @param index
	 *            Id of the company that has been deleted and that will be
	 *            delete in the cache.
	 */
	@SuppressWarnings("unchecked")
	public void removeCompany(Long index) {
		if (cacheRepository.containsKey("companies")) {
			cacheRepository.put("companies", ((List<Company>) cacheRepository.get("companies")).remove(index));
		}
	}

	/**
	 * Set the companies cache.
	 * 
	 * @param companies
	 *            The company list that will be set as cache.
	 */
	public void setCompanies(List<Company> companies) {
		cacheRepository.put("companies", companies);
	}

	/**
	 * Set the computer count
	 * 
	 * @param count
	 *            The computer count that will be set as cache.
	 */
	public void setCount(Integer count) {
		cacheRepository.put("count", count);
	}

	/**
	 * Get the computer count from the cache.
	 * 
	 * @return The count in the cache if it has been set, null otherwise.
	 */
	public Integer getCount() {
		if (cacheRepository.containsKey("count")) {
			return (Integer) cacheRepository.get("count");
		} else {
			return null;
		}
	}

	/**
	 * Get the list of companies from the cache
	 *
	 * @return The companies in the cache if it has been set, null otherwise.
	 */
	@SuppressWarnings("unchecked")
	public List<Company> getCompany() {
		if (cacheRepository.containsKey("companies")) {
			return (List<Company>) cacheRepository.get("companies");
		} else {
			return null;
		}
	}
}
