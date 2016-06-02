package com.excilys.formation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.entity.Company;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.ComputerDAO;

public class CompanyService implements Service<Company> {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyService.class);
	private static CompanyDAO companyDAO;
	private static ComputerDAO computerDAO;
	private static CompanyService instance = new CompanyService();

	private CompanyService() {
		companyDAO = CompanyDAO.getInstance();
		computerDAO = ComputerDAO.getInstance();
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
	public Page<Company> getPage(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		ConnectionThreadLocal.getInstance().beginTransaction();
		computerDAO.deleteByCompany(id);
		companyDAO.delete(id);
		ConnectionThreadLocal.getInstance().endTransaction();
		ConnectionThreadLocal.getInstance().close();
		// TODO commit transaction
	}

	/*
	 * @Override public List<Company> getPage(PageRequest pageRequest) { int
	 * offset = pageRequest.getLimit() * pageRequest.getPage(); List<Company>
	 * companies = companyDAO.getLimited(offset, pageRequest.getLimit()); return
	 * companies; }
	 */
}
