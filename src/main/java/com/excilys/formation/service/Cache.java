package com.excilys.formation.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.excilys.formation.entity.Company;

/**
 * 
 * @author Neoware
 *
 */
public class Cache {
	private static ConcurrentHashMap<String, Object> cacheRepository = new ConcurrentHashMap<>();;
	private static Cache instance = new Cache();

	private Cache() {

	}

	public void incrementCount() {
		if (cacheRepository.containsKey("count")) {
			cacheRepository.put("count", (Integer) cacheRepository.get("count") + 1);
		}
	}

	public static Cache getInstance() {
		return instance;
	}

	public void decrementCount() {
		if (cacheRepository.containsKey("count")) {
			cacheRepository.put("count", (Integer) cacheRepository.get("count") - 1);
		}
	}

	@SuppressWarnings("unchecked")
	public void addCompany(Company toAdd) {
		if (cacheRepository.containsKey("companies")) {
			cacheRepository.put("companies", ((List<Company>) cacheRepository.get("companies")).add(toAdd));
		}
	}

	@SuppressWarnings("unchecked")
	public void removeCompany(Long index) {
		if (cacheRepository.containsKey("companies")) {
			cacheRepository.put("companies", ((List<Company>) cacheRepository.get("companies")).remove(index));
		}
	}

	public void setCompanies(List<Company> companies) {
		cacheRepository.put("companies", companies);
	}

	public void setCount(Integer count) {
		cacheRepository.put("count", count);
	}

	public Integer getCount() {
		if (cacheRepository.containsKey("count")) {
			return (Integer) cacheRepository.get("count");
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Company> getCompany() {
		if (cacheRepository.containsKey("companies")) {
			return (List<Company>) cacheRepository.get("companies");
		} else {
			return null;
		}
	}
}
