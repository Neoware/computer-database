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
import com.excilys.formation.exception.DaoException;

public class CompanyDAO implements DAO<Company> {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyDAO.class);
	private static ConnectionManager connectionManager;
	private static CompanyDAO instance = new CompanyDAO();

	private CompanyDAO() {
		connectionManager = ConnectionManager.getInstance();
	}

	public static CompanyDAO getInstance() {
		return instance;
	}

	@Override
	public Company find(Long id) {
		Company company = null;
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			String sql = "SELECT * FROM company WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			LOG.info(preparedStatement.toString());
			result = preparedStatement.executeQuery();
			if (result.first()) {
				company = new Company(id, result.getString("name"));
			}
		} catch (SQLException e) {
			LOG.error("Error while searching for company with id " + id, e);
			throw new DaoException("Error while searching for company with id " + id);
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
		return company;
	}

	@Override
	public Company create(Company toCreate) {
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "INSERT INTO computer VALUES (NULL, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toCreate.getName());
			LOG.info(preparedStatement.toString());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			LOG.error("Error while creating a company", e);
			throw new DaoException("Error while creating a company");
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, null);
		}
		return null;
	}

	@Override
	public void update(Company toUpdate) {
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "UPDATE computer SET name=? WHERE id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toUpdate.getName());
			preparedStatement.setLong(2, toUpdate.getId());
			LOG.info(sql);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			LOG.error("Error while updating a company", e);
			throw new DaoException("Error while updating a company");
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, null);
		}
	}

	@Override
	public void delete(Long id) {
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "DELETE from computer where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			LOG.info(sql);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			LOG.error("Error while deleting a company", e);
			throw new DaoException("Error while deleting a company");
		} finally {
			connectionManager.cleanUp(connection, preparedStatement, null);
		}
	}

	@Override
	public List<Company> getAll() {
		List<Company> companies = new ArrayList<>();
		String sql = "SELECT * from company";
		LOG.info(sql);
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) {
				Company temp = new Company();
				temp.setId(result.getLong("id"));
				temp.setName(result.getString("name"));
				companies.add(temp);
			}
		} catch (SQLException e) {
			LOG.error("Error while retrieving all companies", e);
			throw new DaoException("Error while retrieving all companies");
		} finally {
			connectionManager.cleanUp(connection, statement, result);
		}
		return companies;
	}

	@Override
	public List<Company> getLimited(int offset, int limit) {
		List<Company> companies = new ArrayList<>();
		String sql = "SELECT * from company LIMIT " + offset + ", " + limit;
		LOG.info(sql);
		Connection connection = connectionManager.getConnection();
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) {
				Company temp = new Company();
				temp.setId(result.getLong("id"));
				temp.setName(result.getString("name"));
				companies.add(temp);
			}
		} catch (SQLException e) {
			LOG.error("Error when fetching a part of all companies", e);
			throw new DaoException("Error when fetching a part of all companies");
		} finally {
			connectionManager.cleanUp(connection, statement, result);
		}
		return companies;
	}

}
