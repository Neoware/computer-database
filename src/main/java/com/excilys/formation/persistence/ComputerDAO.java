package com.excilys.formation.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.entity.Computer;
import com.excilys.formation.exception.DaoException;
import com.excilys.formation.service.Cache;
import com.excilys.formation.service.PageRequest;
import com.excilys.formation.util.DateUtils;

/**
 * DAO class for the computer table.
 * 
 * @author neoware
 *
 */
@Repository
public class ComputerDAO {

	private static final Logger LOG = LoggerFactory.getLogger(ComputerDAO.class);
	@Autowired
	private DataSource dataSource;
	@Autowired
	private Cache cache;
	private JdbcTemplate jdbcTemplate;

	public ComputerDAO() {
	}

	/**
	 * Get an entity corresponding to a row in the computer table.
	 * 
	 * @param id
	 *            the id of the computer that will be searched.
	 * @return the computer entity if the id exists, null otherwise.
	 * @throws DaoException
	 */
	public Computer find(Long id) {
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, "
				+ "computer.company_id, company.name AS company_name" + " FROM computer " + "LEFT JOIN company"
				+ " ON computer.company_id = company.id" + " WHERE computer.id =? ";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Computer computer = jdbcTemplate.queryForObject(sql, new Object[] { id }, new ComputerRowMapper());
		return computer;
	}

	/**
	 * Create a new computer.
	 * 
	 * @param toCreate
	 *            The computer entity corresponding to the row that will be
	 *            inserted.
	 * @return The computer entity that has been inserted.
	 * @throws DaoException
	 */
	public Computer create(Computer toCreate) {
		String sql = "INSERT INTO computer VALUES (NULL, ?, ?, ?, ?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		int rowAffected = jdbcTemplate.update(sql, new Object[] { toCreate.getName(), toCreate.getIntroduced(),
				toCreate.getDiscontinued(), toCreate.getComputerCompany().getId() });
		if (rowAffected == 1) {
			cache.incrementCount();
		}
		return toCreate;
	}

	/**
	 * Update an already existing computer.
	 * 
	 * @param toUpdate
	 *            Entity containing all attributes that will be updated in the
	 *            database.
	 * @throws DaoException
	 */
	public void update(Computer toUpdate) {
		String sql = "UPDATE computer SET name= ?, introduced= ?, discontinued=?, company_id= ? WHERE  id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql,
				new Object[] { toUpdate.getName(), DateUtils.localDateToTimestamp(toUpdate.getIntroduced()),
						DateUtils.localDateToTimestamp(toUpdate.getDiscontinued()),
						toUpdate.getComputerCompany().getId(), toUpdate.getId() });
	}

	/**
	 * Delete a computer in the database
	 * 
	 * @param id
	 *            The id of the computer that will be deleted.
	 * @throws DaoException
	 */
	public void delete(Long id) {
		String sql = "DELETE from computer where id=?";
		jdbcTemplate.update(sql, new Object[] { id });
	}

	/**
	 * Get all the computers in the database without pagination system.
	 * 
	 * @return A list containing all computers in the database.
	 * @throws DaoException
	 */
	public List<Computer> getAll() {
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, "
				+ "computer.company_id, company.name AS company_name" + "FROM computer " + "INNER JOIN company"
				+ "ON computer.company_id = company.id";
		LOG.info(sql);
		List<Computer> computers = new ArrayList<>();
		computers = jdbcTemplate.query(sql, new ComputerRowMapper());
		return computers;
	}

	/**
	 * Get a page from the database by building a complex request from a
	 * Pagerequest object.
	 * 
	 * @param pageRequest
	 *            the object containg all the parameter to build the SQL request
	 *            like offset, limit, where clause.
	 * @return The list of computers corresponding to the result of the query
	 *         that has been performed.
	 * @throws DaoException
	 */
	public List<Computer> getPage(PageRequest pageRequest) {
		QueryBuilder queryBuilder = new QueryBuilder();
		String sql = queryBuilder.createGetPageQuery(pageRequest);
		LOG.info(sql);
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Computer> computers = new ArrayList<>();
		computers = jdbcTemplate.query(sql, new ComputerRowMapper());
		return computers;
	}

	/**
	 * Retrieve the count of the query build from the pagerequest object without
	 * the limit and offset clause.
	 * 
	 * @param pageRequest
	 *            the object containg all the parameter to build the SQL request
	 *            like where clause.
	 * @return the count that has been retrieved.
	 * @throws DaoException
	 */
	public int getCountElements(PageRequest pageRequest) {
		QueryBuilder queryBuilder = new QueryBuilder();
		String sql = queryBuilder.createGetCountQuery(pageRequest);
		jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;

	}

	/**
	 * Delete all computers that have a specific company as company_id entry.
	 * Useful for the delete company feature.
	 * 
	 * @param companyId
	 *            The id of the company corresponding to the computers that will
	 *            be deleted.
	 * @throws DaoException
	 */
	public void deleteByCompany(Long companyId) {
		String sql = "DELETE FROM computer where company_id = ?";
		jdbcTemplate.update(sql);
	}

	/**
	 * Count the total number of computers in the database.
	 * 
	 * @return the count retrieved from the database.
	 * @throws DaoException
	 */
	public int count() {
		if (cache.getCount() != null) {
			LOG.info("Accessing cache count");
			return cache.getCount();
		}
		String sql = "SELECT COUNT( id )FROM computer";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
}
