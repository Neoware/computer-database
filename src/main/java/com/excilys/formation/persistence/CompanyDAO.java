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
import com.excilys.formation.service.Cache;
import com.excilys.formation.service.ConnectionThreadLocal;

public class CompanyDAO {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyDAO.class);
	private static CompanyDAO instance = new CompanyDAO();
	private static ConnectionManager connectionManager = ConnectionManager.getInstance();

	private CompanyDAO() {
	}

	public static CompanyDAO getInstance() {
		return instance;
	}

	public Company find(Long id) {
		Company company = null;
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
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
			connectionManager.cleanUp(null, preparedStatement, result);
		}
		return company;
	}

	public Company create(Company toCreate) {
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "INSERT INTO computer VALUES (NULL, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toCreate.getName());
			LOG.info(preparedStatement.toString());
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected == 1) {
				Cache.getInstance().addCompany(toCreate);
			}
		} catch (SQLException e) {
			LOG.error("Error while creating a company", e);
			throw new DaoException("Error while creating a company");
		} finally {
			connectionManager.cleanUp(null, preparedStatement, null);
		}
		return null;
	}

	public void update(Company toUpdate) {
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
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
			connectionManager.cleanUp(null, preparedStatement, null);
		}
	}

	public void delete(Long id) {
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "DELETE from company where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			LOG.info(sql);
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected == 1) {
				Cache.getInstance().removeCompany(id);
			}
		} catch (SQLException e) {
			LOG.error("Error while deleting a company rollbacking engaged", e);
			try {
				connection.rollback();
				throw new DaoException("Error while deleting a company rollbacking engaged");
			} catch (SQLException e1) {
				LOG.error("Error when rollbacking");
				throw new DaoException("Error when rollbacking");
			}
		} finally {
			connectionManager.cleanUp(null, preparedStatement, null);
		}
	}

	public List<Company> getAll() {
		if (Cache.getInstance().getCompany() != null) {
			LOG.info("Accessing cache for company");
			return Cache.getInstance().getCompany();
		}
		List<Company> companies = new ArrayList<>();
		String sql = "SELECT * from company";
		LOG.info(sql);
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
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
			connectionManager.cleanUp(null, statement, result);
		}
		return companies;
	}

	public List<Company> getLimited(int offset, int limit) {
		List<Company> companies = new ArrayList<>();
		String sql = "SELECT * from company LIMIT " + offset + ", " + limit;
		LOG.info(sql);
		Connection connection = ConnectionThreadLocal.getInstance().getConnection();
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
			connectionManager.cleanUp(null, statement, result);
		}
		return companies;
	}
}
