package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "admincdb";
	private static final String PASSWORD = "qwerty1234";
	private static ConnectionManager instance;

	private ConnectionManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			synchronized (ConnectionManager.class) {
				if (instance == null) {
					instance = new ConnectionManager();
				}
			}
		}
		return instance;
	}

	public synchronized Connection getConnection() {
		try {
			Connection connect = DriverManager.getConnection(URL, USER, PASSWORD);
			return connect;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void cleanUp(Connection connection, Statement statement, ResultSet resultSet) {
		if (connection != null) {
			try {
				connection.close();
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
