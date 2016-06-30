package com.excilys.formation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.entity.Company;
import com.excilys.formation.exception.DaoException;
import com.excilys.formation.mapper.CompanyMapper;
import com.excilys.formation.persistence.Cache;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.persistence.ComputerDAO;
import com.excilys.formation.persistence.PageRequest;

/**
 * Singleton service layer for company manipulations.
 * 
 * @author Neoware
 *
 */
@Service
@Transactional(rollbackFor = DaoException.class, propagation = Propagation.REQUIRES_NEW)
public class CompanyService {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyService.class);
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private Cache cache;

	public CompanyService() {
	}

	/**
	 * Retrieve a company entity by id.
	 * 
	 * @param id
	 *            The id of the company that will be retrieved.
	 * @return the company entity if the id exists, null otherwise.
	 */
	public Company getById(Long id) {
		Company company = companyDAO.find(id);
		return company;
	}

	/**
	 * Retrieve a list of all company entities
	 * 
	 * @return The list of companies.
	 */
	public List<Company> getAll() {
		List<Company> companies = companyDAO.getAll();
		if (cache.getCompany() == null) {
			cache.setCompanies(companies);
		}
		return companies;
	}

	/**
	 * Get the count of companies in database.
	 * 
	 * @return the count of companies.
	 */

	public int count() {
		// TODO count company
		return 0;
	}

	public Page<CompanyDTO> getPage(PageRequest pageRequest) {
		List<Company> companyList = companyDAO.getPage(pageRequest);
		int count;
		count = companyDAO.count();
		LOG.info("" + count);
		List<CompanyDTO> companyDTOs = CompanyMapper.fromEntitiesToDtos(companyList);
		Page<CompanyDTO> companyPage = new Page<>(companyDTOs, pageRequest, count);
		return companyPage;
	}

	/**
	 * Delete a company and all computers that have this company as company_id.
	 * This operation is done inside a transaction.
	 * 
	 * @param id
	 *            the id of the company that will be deleted
	 */

	public void delete(Long id) {
		computerDAO.deleteByCompany(id);
		companyDAO.delete(id);
	}

	/*
	 * @Override public List<Company> getPage(PageRequest pageRequest) { int
	 * offset = pageRequest.getLimit() * pageRequest.getPage(); List<Company>
	 * companies = companyDAO.getLimited(offset, pageRequest.getLimit()); return
	 * companies; }
	 */
}
