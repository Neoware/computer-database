package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "admincdb";
	private static final String PASSWORD = "qwerty1234";
	private static final Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);
	private static ConnectionManager instance = new ConnectionManager();
	private HikariDataSource hikariDataSource;

	private ConnectionManager() {
		/*
		 * try { Class.forName("com.mysql.jdbc.Driver"); } catch
		 * (ClassNotFoundException e) { LOG.error(
		 * "Impossible to load the mysql jdbc driver, exiting...", e);
		 * System.exit(-1); }
		 */
		HikariConfig config = new HikariConfig("/hikari.properties");
		hikariDataSource = new HikariDataSource(config);
	}

	public static ConnectionManager getInstance() {
		return instance;
	}

	public Connection getConnection() {
		try {
			// Connection connect = DriverManager.getConnection(URL, USER,
			// PASSWORD);
			Connection connection = hikariDataSource.getConnection();
			return connection;
		} catch (SQLException e) {
			LOG.error("Impossible to connect to the database, exiting...", e);
			System.exit(-1);
		}
		return null;

	}

	public void cleanUp(Connection connection, Statement statement, ResultSet resultSet) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error("Not able to close connection", e);
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOG.error("Not able to close statement", e);
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOG.error("Not able to close resultset", e);
			}
		}
	}
}
