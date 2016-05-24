package com.excilys.formation.service;

import java.util.List;

import com.excilys.formation.entity.Company;
import com.excilys.formation.persistence.CompanyDAO;

public class CompanyService implements Service<Company>{
	
	private static CompanyDAO companyDAO;
	private static CompanyService instance;

	private CompanyService(){
		companyDAO = CompanyDAO.getInstance();
	}

	public static CompanyService getInstance() {
		if (instance == null) {
			synchronized (CompanyService.class) {
				if (instance == null) {
					instance = new CompanyService();
				}
			}
		}
		return instance;	
	}
	
	public Company getById(Long id){
		Company company = companyDAO.find(id);
		return company;
	}
	
	public List<Company> getAll() {
		List<Company> companies = companyDAO.getAll();
		return companies;
	}
	
	public List<Company> getSelection(int offset, int limit){
		List<Company> companies = companyDAO.getLimited(offset, limit);
		return companies;
	}

	@Override
	public int count() {
		// TODO count company
		return 0;
	}
}
