package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.exception.DaoException;
import com.excilys.formation.service.Cache;
import com.excilys.formation.service.ConnectionThreadLocal;
import com.excilys.formation.service.PageRequest;
import com.excilys.formation.util.DateUtils;

public class ComputerDAO {

	private static final Logger LOG = LoggerFactory.getLogger(ComputerDAO.class);
	private static ConnectionManager connectionManager;
	private static ComputerDAO instance = new ComputerDAO();

	private ComputerDAO() {
		connectionManager = ConnectionManager.getInstance();
	}

	public static ComputerDAO getInstance() {
		return instance;
	}

	public Computer find(Long id) {
		Computer computer = null;
		String sql = "SELECT computer.name, computer.introduced, computer.discontinued, "
				+ "computer.company_id, company.name AS company_name" + " FROM computer " + "LEFT JOIN company"
				+ " ON computer.company_id = company.id" + " WHERE computer.id =? ";
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			LOG.info(preparedStatement.toString());
			result = preparedStatement.executeQuery();
			if (result.first()) {
				Company company = new Company(result.getLong("company_id"), result.getString("company_name"));
				computer = Computer.getBuilder().name(result.getString("name")).id(id)
						.introduced(DateUtils.timestampToLocalDate(result.getTimestamp("introduced")))
						.discontinued(DateUtils.timestampToLocalDate(result.getTimestamp("discontinued")))
						.computerCompany(company).build();
			}
		} catch (SQLException e) {
			LOG.error("Error while searching for computer with id " + id, e);
			throw new DaoException("Error while searching for computer with id " + id);
		} finally {
			connectionManager.cleanUp(null, preparedStatement, result);
		}
		return computer;
	}

	public Computer create(Computer toCreate) {
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		try {
			String sql = "INSERT INTO computer VALUES (NULL, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, toCreate.getName());
			preparedStatement.setTimestamp(2, DateUtils.localDateToTimestamp(toCreate.getIntroduced()));
			preparedStatement.setTimestamp(3, DateUtils.localDateToTimestamp(toCreate.getDiscontinued()));
			preparedStatement.setObject(4, toCreate.getComputerCompany().getId());
			LOG.info(preparedStatement.toString());
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected == 1) {
				Cache.getInstance().incrementCount();
			}
			generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				toCreate.setId(generatedKeys.getLong(1));
			}
		} catch (SQLException e) {
			LOG.error("Error while creating computer", e);
			throw new DaoException("Error while creating computer");
		} finally {
			connectionManager.cleanUp(null, preparedStatement, generatedKeys);
		}
		return toCreate;
	}

	public void update(Computer toUpdate) {
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "UPDATE computer SET name= ?, introduced= ?, discontinued=?, company_id= ? WHERE  id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toUpdate.getName());
			preparedStatement.setTimestamp(2, DateUtils.localDateToTimestamp(toUpdate.getIntroduced()));
			preparedStatement.setTimestamp(3, DateUtils.localDateToTimestamp(toUpdate.getDiscontinued()));
			preparedStatement.setLong(4, toUpdate.getComputerCompany().getId());
			preparedStatement.setLong(5, toUpdate.getId());
			LOG.info(preparedStatement.toString());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Error while updating a computer", e);
			throw new DaoException("Error while updating a computer");
		} finally {
			connectionManager.cleanUp(null, preparedStatement, null);
		}
	}

	public void delete(Long id) {
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "DELETE from computer where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			LOG.info(preparedStatement.toString());
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected == 1) {
				Cache.getInstance().decrementCount();
			}
		} catch (SQLException e) {
			LOG.error("Error while deleting a computer", e);
			throw new DaoException("Error while deleting a computer");
		} finally {
			connectionManager.cleanUp(null, preparedStatement, null);
		}
	}

	public List<Computer> getAll() {
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, "
				+ "computer.company_id, company.name AS company_name" + "FROM computer " + "INNER JOIN company"
				+ "ON computer.company_id = company.id";
		LOG.info(sql);
		List<Computer> computers = new ArrayList<>();
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) {
				Company company = new Company(result.getLong("company_id"), result.getString("company_name"));
				Computer computer = Computer.getBuilder().name(result.getString("name")).id(result.getLong("id"))
						.introduced(DateUtils.timestampToLocalDate(result.getTimestamp("introduced")))
						.discontinued(DateUtils.timestampToLocalDate(result.getTimestamp("discontinued")))
						.computerCompany(company).build();
				computers.add(computer);
			}
		} catch (SQLException e) {
			LOG.error("Error when retrieving all the computers", e);
			throw new DaoException("Error when retrieving all the computers");
		} finally {
			connectionManager.cleanUp(null, statement, result);
		}
		return computers;
	}

	public List<Computer> getPage(PageRequest pageRequest) {
		List<Computer> computers = new ArrayList<>();
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		ResultSet result = null;
		QueryBuilder queryBuilder = new QueryBuilder();
		PreparedStatement preparedStatement = queryBuilder.createGetPageQuery(pageRequest, connection);
		try {
			result = preparedStatement.executeQuery();
			while (result.next()) {
				Company company = new Company(result.getLong("company_id"), result.getString("company_name"));
				Computer computer = Computer.getBuilder().name(result.getString("name")).id(result.getLong("id"))
						.introduced(DateUtils.timestampToLocalDate(result.getTimestamp("introduced")))
						.discontinued(DateUtils.timestampToLocalDate(result.getTimestamp("discontinued")))
						.computerCompany(company).build();
				computers.add(computer);
			}
		} catch (SQLException e) {
			LOG.error("Error when fetching a part of all computers", e);
			throw new DaoException("Error when fetching a part of all computers");
		} finally {
			connectionManager.cleanUp(null, preparedStatement, result);
		}
		return computers;
	}

	public int getCountElements(PageRequest pageRequest) {
		QueryBuilder queryBuilder = new QueryBuilder();
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		PreparedStatement preparedStatement = queryBuilder.createGetCountQuery(pageRequest, connection);
		ResultSet result;
		int count = -1;
		try {
			result = preparedStatement.executeQuery();
			if (result.next()) {
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error("Error when getting count elements", e);
			throw new DaoException("Error when getting count elements", e);
		}
		finally {
			connectionManager.cleanUp(null, preparedStatement, null);
		}
		return count;
	}

	public void deleteByCompany(Long companyId) {
		String sql = "DELETE FROM computer where company_id = ?";
		PreparedStatement preparedStatement = null;
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, companyId);
			LOG.info(preparedStatement.toString());
			int rowAffected = preparedStatement.executeUpdate();
			// TODO decrement by rowAffected;

		} catch (SQLException e) {
			LOG.error("Error when deleting computers by company, rollback engaged");
			try {
				connection.rollback();
				throw new DaoException("Error when deleting computers by company");
			} catch (SQLException e1) {
				LOG.error("Error when rollbacking");
				throw new DaoException("Error when rollbacking");
			}
			finally {
				connectionManager.cleanUp(null, preparedStatement, null);
			}
		}
	}

	public int count() {
		if (Cache.getInstance().getCount() != null) {
			LOG.info("Accessing cache count");
			return Cache.getInstance().getCount();	
		} else {
			Connection connection = ConnectionThreadLocal.getInstance().getConnection();
			String sql = "SELECT COUNT( id )FROM computer";
			LOG.info(sql);
			int count = -1;
			Statement statement = null;
			ResultSet result = null;
			try {
				statement = connection.createStatement();
				result = statement.executeQuery(sql);
				if (result.next()) {
					count = result.getInt(1);
				}
			} catch (SQLException e) {
				LOG.error("Error when getting the count computers count", e);
				throw new DaoException("Error when getting the computers count");
			} finally {
				connectionManager.cleanUp(null, statement, result);
			}
			return count;
		}
	}

}
