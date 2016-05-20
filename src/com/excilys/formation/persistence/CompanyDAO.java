package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.entity.Company;


public class CompanyDAO implements DAO<Company>{

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet result;
	private Statement statement;
	
	public CompanyDAO() {
	}
	
	@Override
	public Company find(Long id) {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM company WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			result = preparedStatement.executeQuery();
			if (result.first()){
				Company company = new Company(id, result.getString("name"));
				return company;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			ConnectionManager connectionManager =  ConnectionManager.getInstance();
			connectionManager.cleanUp(connection, preparedStatement, result);
		}
		return null;
	}

	@Override
	public Company create(Company toCreate) {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO computer VALUES (NULL, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toCreate.getName());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.getInstance().cleanUp(connection, preparedStatement, result);
		}
		return null;
	}

	@Override
	public Company update(Company toUpdate) {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE computer SET name=? WHERE id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toUpdate.getName());
			preparedStatement.setLong(2, toUpdate.getId());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.getInstance().cleanUp(connection, preparedStatement, result);
		}
		return null;
	}

	@Override
	public Company delete(Long id) {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			String sql = "DELETE from computer where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.getInstance().cleanUp(connection, preparedStatement, result);
		}
		return null;
	}

	@Override
	public List<Company> getAll() {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			statement = connection.createStatement();
			String sql = "SELECT * from company";
			result = statement.executeQuery(sql);
			List<Company> companies = new ArrayList<>();
			while(result.next()) {
					Company temp = new Company();
					temp.setId(result.getLong("id"));
					temp.setName(result.getString("name"));
					companies.add(temp);
				} 
			return companies;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.getInstance().cleanUp(connection, statement, result);
		}
		return null;
	}
	
	
	

}
