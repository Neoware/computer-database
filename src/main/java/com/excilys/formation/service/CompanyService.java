package com.excilys.formation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.entity.Company;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.ComputerDAO;

/**
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

	public static CompanyService getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Company getById(Long id) {
		connectionThreadLocal.initConnection();
		Company company = companyDAO.find(id);
		connectionThreadLocal.close();
		return company;
	}

	/**
	 * 
	 * @return
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
	 * 
	 */
	@Override
	public int count() {
		connectionThreadLocal.initConnection();
		connectionThreadLocal.close();
		// TODO count company
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public Page<Company> getPage(PageRequest pageRequest) {
		connectionThreadLocal.initConnection();
		connectionThreadLocal.close();
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param id
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
