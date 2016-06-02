package com.excilys.formation.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.excilys.formation.entity.Company;

public class Cache {

	private static ConcurrentHashMap<String, Object> cacheRepository;

	static {
		cacheRepository = new ConcurrentHashMap<>();
		cacheRepository.put("count", ComputerService.getInstance().count());
		cacheRepository.put("companies", CompanyService.getInstance().getAll());
	}

	public static void incrementCount() {
		cacheRepository.put("count", (Integer) cacheRepository.get("count") + 1);
	}

	public static void decrementCount() {
		cacheRepository.put("count", (Integer) cacheRepository.get("count") - 1);
	}

	@SuppressWarnings("unchecked")
	public static void addCompany(Company toAdd) {
		cacheRepository.put("companies", ((List<Company>) cacheRepository.get("companies")).add(toAdd));
	}

	@SuppressWarnings("unchecked")
	public static void removeCompany(Long index) {
		cacheRepository.put("companies", ((List<Company>) cacheRepository.get("companies")).remove(index));
	}

	public static Integer getCount() {
		return (Integer) cacheRepository.get("count");
	}

	@SuppressWarnings("unchecked")
	public static List<Company> getCompany() {
		return (List<Company>) cacheRepository.get("companies");
	}
}
