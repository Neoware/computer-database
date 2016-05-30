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
import com.excilys.formation.util.DateUtils;

public class ComputerDAO implements DAO<Computer> {

	private static final Logger LOG = LoggerFactory.getLogger(ComputerDAO.class);
	private static ConnectionManager connectionManager;
	private static ComputerDAO instance = new ComputerDAO();

	private ComputerDAO() {
		connectionManager = ConnectionManager.getInstance();
	}

	public static ComputerDAO getInstance() {
		return instance;
	}

	@Override
	public Computer find(Long id) {
		Computer computer = null;
		String sql = "SELECT computer.name, computer.introduced, computer.discontinued, "
				+ "computer.company_id, company.name AS company_name" + " FROM computer " + "LEFT JOIN company"
				+ " ON computer.company_id = company.id" + " WHERE computer.id =? ";
		Connection connection = connectionManager.getConnection();
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
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
		return computer;
	}

	@Override
	public Computer create(Computer toCreate) {
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		try {
			connection = connectionManager.getConnection();
			String sql = "INSERT INTO computer VALUES (NULL, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, toCreate.getName());
			preparedStatement.setTimestamp(2, DateUtils.localDateToTimestamp(toCreate.getIntroduced()));
			preparedStatement.setTimestamp(3, DateUtils.localDateToTimestamp(toCreate.getDiscontinued()));
			preparedStatement.setObject(4, toCreate.getComputerCompany().getId());
			LOG.info(preparedStatement.toString());
			preparedStatement.executeUpdate();
			generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				toCreate.setId(generatedKeys.getLong(1));
			}
		} catch (SQLException e) {
			LOG.error("Error while creating computer", e);
			throw new DaoException("Error while creating computer");
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, generatedKeys);
		}
		return toCreate;
	}

	@Override
	public void update(Computer toUpdate) {
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionManager.getConnection();
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
			connectionManager.cleanUp(connection, preparedStatement, null);
		}
	}

	@Override
	public void delete(Long id) {
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionManager.getConnection();
			String sql = "DELETE from computer where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			LOG.info(preparedStatement.toString());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Error while deleting a computer", e);
			throw new DaoException("Error while deleting a computer");
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, null);
		}
	}

	@Override
	public List<Computer> getAll() {
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, "
				+ "computer.company_id, company.name AS company_name" + "FROM computer " + "INNER JOIN company"
				+ "ON computer.company_id = company.id";
		LOG.info(sql);
		List<Computer> computers = new ArrayList<>();
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = connectionManager.getConnection();
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
			connectionManager.cleanUp(connection, statement, result);
		}
		return computers;
	}

	@Override
	public List<Computer> getLimited(int offset, int limit) {
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, "
				+ "company.name AS company_name FROM computer "
				+ "LEFT JOIN company ON computer.company_id = company.id " + " LIMIT " + offset + ", " + limit;
		LOG.info(sql);
		List<Computer> computers = new ArrayList<>();
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = connectionManager.getConnection();
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
			LOG.error("Error when fetching a part of all computers", e);
			throw new DaoException("Error when fetching a part of all computers");
		} finally {
			connectionManager.cleanUp(connection, statement, result);
		}
		return computers;
	}

	public int count() {
		String sql = "SELECT COUNT( id )FROM computer";
		LOG.info(sql);
		int count = -1;
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = connectionManager.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			if (result.next()) {
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error("Error when getting the count computers count", e);
			throw new DaoException("Error when getting the computers count");
		} finally {
			connectionManager.cleanUp(connection, statement, result);
		}
		return count;
	}

}
