package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ComputerDAO implements DAO<Computer> {
	
	private Connection connection;
	
	public ComputerDAO() {	
			connection = ConnectionManager.getInstance();
	}
	
	@Override
	public Computer find(int id) {
		try {
			String sql = "SELECT * FROM computer WHERE id =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if (result.first()){
				Computer computer = new Computer(id, result.getString("name"), 
						result.getTimestamp("introduced"), result.getTimestamp("discontinued"), result.getInt("company_id"));
				return computer;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public Computer create(Computer toCreate) {
		try {
			String sql = "INSERT INTO computer VALUES (NULL, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toCreate.getName());
			preparedStatement.setTimestamp(2, toCreate.getIntroduced());
			preparedStatement.setTimestamp(3, toCreate.getDiscontinued());
			preparedStatement.setInt(4, toCreate.getCompanyId());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Computer update(Computer toUpdate) {
		try {
			String sql = "UPDATE computer SET name= ?, introduced= ?, discontinued=?, company_id= ? WHERE  id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toUpdate.getName());
			preparedStatement.setTimestamp(2, toUpdate.getIntroduced());
			preparedStatement.setTimestamp(3, toUpdate.getDiscontinued());
			preparedStatement.setInt(4, toUpdate.getCompanyId());
			preparedStatement.setInt(5, toUpdate.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Computer delete(int id) {
		try {
			String sql = "DELETE from computer where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getAll() {
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * from computer";
			ResultSet result = statement.executeQuery(sql);
			List <Computer> computers = new ArrayList<>();
			while(result.next()) {
				Computer temp = new Computer();
				temp.setId(result.getInt("id"));
				temp.setName(result.getString("name"));
				temp.setIntroduced(result.getTimestamp("introduced"));
				temp.setDiscontinued(result.getTimestamp("discontinued"));
				temp.setCompanyId(result.getInt("id"));
				computers.add(temp);
			} 
		return computers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
