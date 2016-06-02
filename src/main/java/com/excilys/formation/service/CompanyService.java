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
		ConnectionThreadLocal.getInstance().initConnection();
		Company company = companyDAO.find(id);
		ConnectionThreadLocal.getInstance().close();
		return company;
	}

	/**
	 * 
	 * @return
	 */
	public List<Company> getAll() {
		ConnectionThreadLocal.getInstance().initConnection();
		List<Company> companies = companyDAO.getAll();
		ConnectionThreadLocal.getInstance().close();
		return companies;
	}

	/**
	 * 
	 */
	@Override
	public int count() {
		ConnectionThreadLocal.getInstance().initConnection();
		ConnectionThreadLocal.getInstance().close();
		// TODO count company
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public Page<Company> getPage(PageRequest pageRequest) {
		ConnectionThreadLocal.getInstance().initConnection();
		ConnectionThreadLocal.getInstance().close();
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		ConnectionThreadLocal.getInstance().initConnection();
		ConnectionThreadLocal.getInstance().beginTransaction();
		computerDAO.deleteByCompany(id);
		companyDAO.delete(id);
		ConnectionThreadLocal.getInstance().commit();
		ConnectionThreadLocal.getInstance().endTransaction();
		ConnectionThreadLocal.getInstance().close();
	}

	/*
	 * @Override public List<Company> getPage(PageRequest pageRequest) { int
	 * offset = pageRequest.getLimit() * pageRequest.getPage(); List<Company>
	 * companies = companyDAO.getLimited(offset, pageRequest.getLimit()); return
	 * companies; }
	 */
}
