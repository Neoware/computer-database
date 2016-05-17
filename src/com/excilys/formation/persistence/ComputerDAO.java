package com.excilys.formation.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ComputerDAO extends DAO<Computer> {

	public ComputerDAO() {}
	
	@Override
	public Computer find(int id) {
		try {
			statement = connection.createStatement();
			String sql = "SELECT * FROM computer WHERE id = " + id;
			ResultSet result = statement.executeQuery(sql);
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
			statement = connection.createStatement();
			String sql = "INSERT INTO computer VALUES (NULL, \""+ toCreate.getName() + "\",\"" + toCreate.getIntroduced()+ "\", \""+ toCreate.getDiscontinued() +"\","+ " \"" + toCreate.getCompany_id()+ "\")"; 
			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Computer update(Computer toUpdate) {
		try {
			statement = connection.createStatement();
			String sql = "UPDATE computer SET name=\""+ toUpdate.getName() + "\", introduced=\"" +toUpdate.getIntroduced() + "\", discontinued=\""+ toUpdate.getDiscontinued() +"\", company_id=\""+ toUpdate.getCompany_id() +"\" WHERE id =" + toUpdate.getId();
			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Computer delete(int id) {
		try {
			statement = connection.createStatement();
			String sql = "DELETE from computer where id=" + id;
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getAll() {
		try {
			statement = connection.createStatement();
			String sql = "SELECT * from computer";
			ResultSet result = statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
