package com.excilys.formation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.entity.Company;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.ComputerDAO;

/**
 * Singleton service layer for company manipulations.
 * 
 * @author Neoware
 *
 */
public class CompanyService implements Service<Company> {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyService.class);
	private static CompanyDAO companyDAO;
	private static ComputerDAO computerDAO;
	private static CompanyService instance = new CompanyService();
	private static ConnectionThreadLocal connectionThreadLocal = ConnectionThreadLocal.getInstance();

	private CompanyService() {
		companyDAO = CompanyDAO.getInstance();
		computerDAO = ComputerDAO.getInstance();

	}

	/**
	 * Get the unique instance of the CompanyService.
	 * 
	 * @return the singleton instance.
	 */
	public static CompanyService getInstance() {
		return instance;
	}

	/**
	 * Retrieve a company entity by id.
	 * 
	 * @param id
	 *            The id of the company that will be retrieved.
	 * @return the company entity if the id exists, null otherwise.
	 */
	public Company getById(Long id) {
		connectionThreadLocal.initConnection();
		Company company = companyDAO.find(id);
		connectionThreadLocal.close();
		return company;
	}

	/**
	 * Retrieve a list of all company entities
	 * 
	 * @return The list of companies.
	 */
	public List<Company> getAll() {
		connectionThreadLocal.initConnection();
		List<Company> companies = companyDAO.getAll();
		if (Cache.getInstance().getCompany() == null) {
			Cache.getInstance().setCompanies(companies);
		}
		connectionThreadLocal.close();
		return companies;
	}

	/**
	 * Get the count of companies in database.
	 * 
	 * @return the count of companies.
	 */
	@Override
	public int count() {
		connectionThreadLocal.initConnection();
		connectionThreadLocal.close();
		// TODO count company
		return 0;
	}

	@Override
	public Page<Company> getPage(PageRequest pageRequest) {
		connectionThreadLocal.initConnection();
		connectionThreadLocal.close();
		// TODO getPage company
		return null;
	}

	/**
	 * Delete a company and all computers that have this company as company_id.
	 * This operation is done inside a transaction.
	 * 
	 * @param id
	 *            the id of the company that will be deleted
	 */
	public void delete(Long id) {
		connectionThreadLocal.initConnection();
		connectionThreadLocal.beginTransaction();
		computerDAO.deleteByCompany(id);
		companyDAO.delete(id);
		connectionThreadLocal.commit();
		connectionThreadLocal.endTransaction();
		connectionThreadLocal.close();
	}

	/*
	 * @Override public List<Company> getPage(PageRequest pageRequest) { int
	 * offset = pageRequest.getLimit() * pageRequest.getPage(); List<Company>
	 * companies = companyDAO.getLimited(offset, pageRequest.getLimit()); return
	 * companies; }
	 */
}
