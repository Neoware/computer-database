package com.excilys.formation.service;

import com.excilys.formation.persistence.CompanyDAO;

public class CompanyService {
	
	private CompanyDAO companyDAO;

	public CompanyService(){
		companyDAO = new CompanyDAO();
	}
	
	public void listAllCompanies(){
		companyDAO.getAll();
	}
}
