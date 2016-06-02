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
	private static ConcurrentHashMap<String, Object> cacheRepository;
	private static Cache instance = new Cache();
	
	static{
		cacheRepository = new ConcurrentHashMap<>();
		//cacheRepository.put("companies", CompanyService.getInstance().getAll());
		//cacheRepository.put("count", ComputerService.getInstance().count());	
	}
	
	private Cache(){
		
	}
	public void incrementCount() {
		cacheRepository.put("count", (Integer) cacheRepository.get("count") + 1);
	}

	public static Cache getInstance(){
		return instance;
	}
	
	public void decrementCount() {
		cacheRepository.put("count", (Integer) cacheRepository.get("count") - 1);
	}

	@SuppressWarnings("unchecked")
	public void addCompany(Company toAdd) {
		cacheRepository.put("companies", ((List<Company>) cacheRepository.get("companies")).add(toAdd));
	}

	@SuppressWarnings("unchecked")
	public void removeCompany(Long index) {
		cacheRepository.put("companies", ((List<Company>) cacheRepository.get("companies")).remove(index));
	}

	public Integer getCount() {
		return (Integer) cacheRepository.get("count");
	}

	@SuppressWarnings("unchecked")
	public List<Company> getCompany() {
		return (List<Company>) cacheRepository.get("companies");
	}
}
