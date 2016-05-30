package com.excilys.formation.service;

import java.util.List;

import com.excilys.formation.entity.Company;
import com.excilys.formation.persistence.CompanyDAO;

public class CompanyService implements Service<Company> {

	private static CompanyDAO companyDAO;
	private static CompanyService instance = new CompanyService();

	private CompanyService() {
		companyDAO = CompanyDAO.getInstance();
	}

	public static CompanyService getInstance() {
		return instance;
	}

	public Company getById(Long id) {
		Company company = companyDAO.find(id);
		return company;
	}

	public List<Company> getAll() {
		List<Company> companies = companyDAO.getAll();
		return companies;
	}

	@Override
	public int count() {
		// TODO count company
		return 0;
	}

	@Override
	public List<Company> getPage(PageRequest pageRequest) {
		int offset = pageRequest.getLimit() * pageRequest.getPage();
		List<Company> companies = companyDAO.getLimited(offset, pageRequest.getLimit());
		return companies;
	}
}
