package com.excilys.formation.service;

import java.util.List;

import com.excilys.formation.persistence.Company;
import com.excilys.formation.persistence.CompanyDAO;

public class CompanyServiceCLI implements CompanyService{
	
	private CompanyDAO companyDAO;

	public CompanyServiceCLI(){
		companyDAO = new CompanyDAO();
	}
	
	public void listAllCompanies(){
		List <Company> companies = companyDAO.getAll();
		for (Company company : companies){
			System.out.println(company);
		}
	}
}
