package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.entity.Computer;


public class ComputerDAO implements DAO<Computer> {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet result;
	private Statement statement;

	public ComputerDAO() {
		
	}

	@Override
	public Computer find(Long id) {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM computer WHERE id =?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			result = preparedStatement.executeQuery();
			if (result.first()){
				Computer computer = new Computer(id, result.getString("name"), 
						result.getTimestamp("introduced").toLocalDate(), result.getTimestamp("discontinued").toLocalDateTime(), result.getLong("company_id"));
				return computer;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;	
		}
		finally {
			ConnectionManager.getInstance().cleanUp(connection, preparedStatement, result);
		}
		return null;
	}

	@Override
	public Computer create(Computer toCreate) {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO computer VALUES (NULL, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, toCreate.getName());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(toCreate.getIntroduced()));
			preparedStatement.setTimestamp(3, Timestamp.valueOf(toCreate.getDiscontinued()));
			preparedStatement.setLong(4, toCreate.getCompanyId());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();		
			if (generatedKeys.next()){
				toCreate.setId(generatedKeys.getLong(1));
				System.out.println("test");
			}
			generatedKeys.close();
			return toCreate;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			ConnectionManager.getInstance().cleanUp(connection, preparedStatement, result);
		}
	}

	@Override
	public Computer update(Computer toUpdate) {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE computer SET name= ?, introduced= ?, discontinued=?, company_id= ? WHERE  id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, toUpdate.getName());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(toUpdate.getIntroduced()));
			preparedStatement.setTimestamp(3, Timestamp.valueOf(toUpdate.getDiscontinued()));
			preparedStatement.setLong(4, toUpdate.getCompanyId());
			preparedStatement.setLong(5, toUpdate.getId());
			preparedStatement.executeUpdate();
			return toUpdate;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			ConnectionManager.getInstance().cleanUp(connection, preparedStatement, result);
		}
	}

	@Override
	public Computer delete(Long id) {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			Computer toDelete = this.find(id);
			String sql = "DELETE from computer where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, toDelete.getId());
			preparedStatement.executeUpdate();
			return toDelete;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.getInstance().cleanUp(connection, preparedStatement, result);
		}
		return null;
	}

	@Override
	public List<Computer> getAll() {
		try {
			connection =  ConnectionManager.getInstance().getConnection();
			statement = connection.createStatement();
			String sql = "SELECT * from computer";
			ResultSet result = statement.executeQuery(sql);
			List <Computer> computers = new ArrayList<>();
			while(result.next()) {
				Computer temp = new Computer();
				temp.setId(result.getLong("id"));
				temp.setName(result.getString("name"));
				temp.setIntroduced(result.getTimestamp("introduced").toLocalDateTime());
				temp.setDiscontinued(result.getTimestamp("discontinued").toLocalDateTime());
				temp.setCompanyId(result.getLong("id"));
				computers.add(temp);
			} 
			return computers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.getInstance().cleanUp(connection, statement, result);
		}
		return null;
	}



}
