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
import com.excilys.formation.utils.DateUtils;

public class ComputerDAO implements DAO<Computer> {

	private static final Logger LOG = LoggerFactory.getLogger(ComputerDAO.class);
	private Connection connection;
	private static ConnectionManager connectionManager;
	private PreparedStatement preparedStatement;
	private ResultSet result;
	private Statement statement;
	private static ComputerDAO instance;

	private ComputerDAO() {
		connectionManager = ConnectionManager.getInstance();
	}

	public static ComputerDAO getInstance() {
		if (instance == null) {
			synchronized (ComputerDAO.class) {
				if (instance == null) {
					instance = new ComputerDAO();
				}
			}
		}
		return instance;
	}

	@Override
	public Computer find(Long id) {
		Computer computer = null;
		String sql = "SELECT computer.name, computer.introduced, computer.discontinued, "
				+ "computer.company_id, company.name AS company_name" + " FROM computer " + "LEFT JOIN company"
				+ " ON computer.company_id = company.id" + " WHERE computer.id =? ";
		try {
			connection = connectionManager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			LOG.debug(preparedStatement.toString());
			result = preparedStatement.executeQuery();
			if (result.first()) {
				Company company = new Company(result.getLong("company_id"), result.getString("company_name"));
				computer = new Computer.ComputerBuilder(result.getString("name")).id(id)
						.introduced(DateUtils.timestampToLocalDate(result.getTimestamp("introduced")))
						.discontinued(DateUtils.timestampToLocalDate(result.getTimestamp("discontinued")))
						.computerCompany(company).build();
			}
		} catch (SQLException e) {
			LOG.error("Error while searching for computer with id " + id, e);
			// throw new DAOException(e);
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
		return computer;
	}

	@Override
	public Computer create(Computer toCreate) {
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
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				toCreate.setId(generatedKeys.getLong(1));
			}
			generatedKeys.close();
		} catch (SQLException e) {
			LOG.error("Error while creating computer", e);
			// throw new DAOException(e);
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
		return toCreate;
	}

	@Override
	public void update(Computer toUpdate) {
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
			// throw new DAOException(e);
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			connection = connectionManager.getConnection();
			String sql = "DELETE from computer where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Error while deleting a computer", e);
			// throw new DAOException(e);
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
	}

	@Override
	public List<Computer> getAll() {
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, "
				+ "computer.company_id, company.name AS company_name" + "FROM computer " + "INNER JOIN company"
				+ "ON computer.company_id = company.id";
		List<Computer> computers = new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Company company = new Company(result.getLong("company_id"), result.getString("company_name"));
				Computer computer = new Computer.ComputerBuilder(result.getString("name")).id(result.getLong("id"))
						.introduced(DateUtils.timestampToLocalDate(result.getTimestamp("introduced")))
						.discontinued(DateUtils.timestampToLocalDate(result.getTimestamp("discontinued")))
						.computerCompany(company).build();
				computers.add(computer);
			}
		} catch (SQLException e) {
			LOG.error("Error when retrieving all of the computers", e);
			// throw new DAOException(e);
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
		System.out.println(sql);
		List<Computer> computers = new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Company company = new Company(result.getLong("company_id"), result.getString("company_name"));
				Computer computer = new Computer.ComputerBuilder(result.getString("name")).id(result.getLong("id"))
						.introduced(DateUtils.timestampToLocalDate(result.getTimestamp("introduced")))
						.discontinued(DateUtils.timestampToLocalDate(result.getTimestamp("discontinued")))
						.computerCompany(company).build();
				computers.add(computer);
			}
		} catch (SQLException e) {
			LOG.error("Error when fetching a part of all computers", e);
			// throw new DAOException(e);
		} finally {
			connectionManager.cleanUp(connection, statement, result);
		}
		return computers;
	}

	public int count() {
		String sql = "SELECT COUNT( id )FROM computer";
		int count = -1;
		try {
			connection = connectionManager.getConnection();
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if (result.next()) {
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error("Error when getting the count computers", e);
		} finally {
			connectionManager.cleanUp(connection, statement, result);
		}
		return count;
	}

}
