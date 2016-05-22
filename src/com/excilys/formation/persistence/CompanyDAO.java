package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;


public class CompanyDAO implements DAO<Company>{

	private Connection connection;
	private static ConnectionManager connectionManager;
	private PreparedStatement preparedStatement;
	private ResultSet result;
	private Statement statement;
	private static CompanyDAO instance;
	
	private CompanyDAO() {
		connectionManager = ConnectionManager.getInstance();
	}
	
	public static CompanyDAO getInstance(){
		if (instance == null){
			synchronized (CompanyDAO.class) {
				if (instance == null){
					instance = new CompanyDAO();
				}
			}
		}
		return instance;
	}
	
	@Override
	public Company find(Long id) {
		Company company = null;
		try {
			connection =  connectionManager.getConnection();
			String sql = "SELECT * FROM company WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			result = preparedStatement.executeQuery();
			if (result.first()){
				company = new Company(id, result.getString("name"));
			}			
		} catch (SQLException e) {
			//LOG.error("");
			//throw new DAOException(e);
		}
		finally {
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
		return company;
	}

	@Override
	public Company create(Company toCreate) {
		try {
			connection =  connectionManager.getConnection();
			String sql = "INSERT INTO computer VALUES (NULL, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toCreate.getName());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			//LOG.error("");
			//throw new DAOException(e);
		}
		finally {
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
		return null;
	}

	@Override
	public void update(Company toUpdate) {
		try {
			connection =  connectionManager.getConnection();
			String sql = "UPDATE computer SET name=? WHERE id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toUpdate.getName());
			preparedStatement.setLong(2, toUpdate.getId());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			connection =  connectionManager.getConnection();
			String sql = "DELETE from computer where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			//LOG.error("");
			//throw new DAOException(e);
		}
		finally {
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
	}

	@Override
	public List<Company> getAll() {
		List<Company> companies = new ArrayList<>();
		String sql = "SELECT * from company";
		try {
			connection =  connectionManager.getConnection();
			statement = connection.createStatement();	
			result = statement.executeQuery(sql);
			while(result.next()) {
					Company temp = new Company();
					temp.setId(result.getLong("id"));
					temp.setName(result.getString("name"));
					companies.add(temp);
				} 
		} catch (SQLException e) {
			//LOG.error("");
			//throw new DAOException(e);
		}
		finally {
			connectionManager.cleanUp(connection, statement, result);
		}
		return companies;
	}

	@Override
	public List<Company> getLimited(int offset, int limit) {
		List<Company> companies = new ArrayList<>();
		String sql = "SELECT * from company LIMIT " + limit + " OFFSET " + offset;
		try {
			connection =  connectionManager.getConnection();
			statement = connection.createStatement();	
			result = statement.executeQuery(sql);
			while(result.next()) {
					Company temp = new Company();
					temp.setId(result.getLong("id"));
					temp.setName(result.getString("name"));
					companies.add(temp);
				} 
		} catch (SQLException e) {
			//LOG.error("");
			//throw new DAOException(e);
		}
		finally {
			connectionManager.cleanUp(connection, statement, result);
		}
		return companies;
	}
	
	
	

}
