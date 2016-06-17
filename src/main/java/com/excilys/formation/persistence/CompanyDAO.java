package com.excilys.formation.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.entity.Company;
import com.excilys.formation.exception.DaoException;
import com.excilys.formation.service.Cache;

/**
 * DAO class for the company table.
 * 
 * @author neoware
 *
 */
@Repository
public class CompanyDAO {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyDAO.class);
	@Autowired
	private DataSource dataSource;
	@Autowired
	private Cache cache;
	private JdbcTemplate jdbcTemplate;

	public CompanyDAO() {
	}

	/**
	 * Get a row in the company table by id.
	 * 
	 * @param id
	 *            the id of the company that is searched.
	 * @return the company entity if the id exists, null otherwise.
	 * @throws DaoException
	 */
	public Company find(Long id) {
		String sql = "SELECT * FROM company WHERE id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Company company = jdbcTemplate.queryForObject(sql, new Object[] { id }, new CompanyRowMapper());
		return company;
	}

	/**
	 * Delete a company in the database and all the computers that have this
	 * company as company_id
	 * 
	 * @param id
	 *            the id of the company that will be deleted
	 * @throws DaoException
	 */
	public void delete(Long id) {
		String sql = "DELETE from company where id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		int rowAffected = jdbcTemplate.update(sql, new Object[] { id });
		if (rowAffected == 1) {
			cache.removeCompany(id);
		}
	}

	/**
	 * Get all company without a pagination system.
	 * 
	 * @return a list containing all companies from the database mapped on
	 *         entities.
	 * @throws DaoException
	 */
	public List<Company> getAll() {
		if (cache.getCompany() != null) {
			LOG.info("Accessing cache for company");
			return cache.getCompany();
		}
		List<Company> companies = new ArrayList<>();
		String sql = "SELECT * from company";
		jdbcTemplate = new JdbcTemplate(dataSource);
		companies = jdbcTemplate.query(sql, new CompanyRowMapper());
		return companies;
	}

	/**
	 * Get a part of the company (useful for the pagination system)
	 * 
	 * @param offset
	 *            the offset of the request
	 * @param limit
	 *            the limit of the request
	 * @return a list of companies that resulted from the request done with
	 *         offset and limit.
	 * @throws DaoException
	 */
	public List<Company> getLimited(int offset, int limit) {
		List<Company> companies = new ArrayList<>();
		String sql = "SELECT * from company LIMIT " + offset + ", " + limit;
		jdbcTemplate = new JdbcTemplate(dataSource);
		companies = jdbcTemplate.query(sql, new Object[] { offset, limit }, new CompanyRowMapper());
		return companies;
	}
}
