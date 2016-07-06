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
import com.excilys.formation.service.interfaces.CompanyService;

/**
 * Singleton service layer for company manipulations.
 * 
 * @author Neoware
 *
 */
@Service
@Transactional(rollbackFor = DaoException.class, propagation = Propagation.REQUIRES_NEW)
public class CompanyServiceImpl implements CompanyService {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyServiceImpl.class);
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private Cache cache;

	public CompanyServiceImpl() {
	}

	@Override
	public Company getById(Long id) {
		Company company = companyDAO.find(id);
		return company;
	}

	@Override
	public List<Company> getAll() {
		List<Company> companies = companyDAO.getAll();
		if (cache.getCompany() == null) {
			cache.setCompanies(companies);
		}
		return companies;
	}

	@Override
	public int count() {
		// TODO count company
		return 0;
	}

	@Override
	public Page<CompanyDTO> getPage(PageRequest pageRequest) {
		List<Company> companyList = companyDAO.getPage(pageRequest);
		int count;
		count = companyDAO.count();
		LOG.info("" + count);
		List<CompanyDTO> companyDTOs = CompanyMapper.fromEntitiesToDtos(companyList);
		Page<CompanyDTO> companyPage = new Page<>(companyDTOs, pageRequest, count);
		return companyPage;
	}

	@Override
	public void delete(Long id) {
		computerDAO.deleteByCompany(id);
		companyDAO.delete(id);
	}
}
