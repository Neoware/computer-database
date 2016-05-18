package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CompanyDAO implements DAO<Company>{

	private Connection connection;
	
	public CompanyDAO() {
		connection = ConnectionManager.getInstance();
	}
	
	@Override
	public Company find(int id) {
		try {
			String sql = "SELECT * FROM company WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if (result.first()){
				Company company = new Company(id, result.getString("name"));
				return company;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public Company create(Company toCreate) {
		try {
		
			String sql = "INSERT INTO computer VALUES (NULL, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toCreate.getName());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Company update(Company toUpdate) {
		try {
			String sql = "UPDATE computer SET name=? WHERE id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toUpdate.getName());
			preparedStatement.setInt(2, toUpdate.getId());
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Company delete(int id) {
		try {
			String sql = "DELETE from computer where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Company> getAll() {
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * from company";
			ResultSet result = statement.executeQuery(sql);
			List<Company> companies = new ArrayList<>();
			while(result.next()) {
					Company temp = new Company();
					temp.setId(result.getInt("id"));
					temp.setName(result.getString("name"));
					companies.add(temp);
				} 
			return companies;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
