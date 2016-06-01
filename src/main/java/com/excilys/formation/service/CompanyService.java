package com.excilys.formation.service;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.entity.Company;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.persistence.ConnectionManager;

public class CompanyService implements Service<Company> {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyService.class);
	private static CompanyDAO companyDAO;
	private static ComputerDAO computerDAO;
	private static CompanyService instance = new CompanyService();
	private static ConnectionManager connectionManager;// trash

	private CompanyService() {
		companyDAO = CompanyDAO.getInstance();
		computerDAO = ComputerDAO.getInstance();
		connectionManager = ConnectionManager.getInstance();
	}

	public static CompanyService getInstance() {
		return instance;
	}

	public Company getById(Long id) {
		Connection connection = connectionManager.getConnection();
		Company company = companyDAO.find(id, connection);
		return company;
	}

	public List<Company> getAll() {
		Connection connection = connectionManager.getConnection();
		List<Company> companies = companyDAO.getAll(connection);
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
		Connection connection = connectionManager.getConnection();
		connectionManager.beginTransaction(connection);
		computerDAO.deleteByCompany(id, connection);
		companyDAO.delete(id, connection);
		connectionManager.commitTransaction(connection);
		connectionManager.endTransaction(connection);
	}

	/*
	 * @Override public List<Company> getPage(PageRequest pageRequest) { int
	 * offset = pageRequest.getLimit() * pageRequest.getPage(); List<Company>
	 * companies = companyDAO.getLimited(offset, pageRequest.getLimit()); return
	 * companies; }
	 */
}
