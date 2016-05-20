package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

	private static final String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String user = "admincdb";
	private static final String password = "qwerty1234";
	private static ConnectionManager manager;
	
	private ConnectionManager(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static ConnectionManager getInstance(){
		if (manager == null)
			manager = new ConnectionManager();
		return manager;
	}
	
	public synchronized Connection getConnection(){
		try {
			Connection connect = DriverManager.getConnection(url, user, password);
			return connect;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void cleanUp(Connection connection, Statement statement, ResultSet resultSet){
		if (connection != null)
			try {
				connection.close();
				if (statement != null)
					statement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
