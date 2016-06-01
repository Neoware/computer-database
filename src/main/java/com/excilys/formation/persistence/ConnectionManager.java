package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.exception.TransactionException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {
	private static final Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);
	private static ConnectionManager instance = new ConnectionManager();
	private static HikariDataSource hikariDataSource;

	private ConnectionManager() {
		HikariConfig config = new HikariConfig("/hikari.properties");
		hikariDataSource = new HikariDataSource(config);
	}

	public static ConnectionManager getInstance() {
		return instance;
	}

	public Connection getConnection() {
		try {
			LOG.info("Asking for a connection");
			return hikariDataSource.getConnection();
		} catch (SQLException e) {
			LOG.error("Impossible to connect to the database, exiting...", e);
			System.exit(-1);
		}
		return null;

	}

	public void cleanUp(Connection connection, Statement statement, ResultSet resultSet) {
		if (connection != null) {
			try {
				LOG.info("Liberating connection");
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

	public void beginTransaction(Connection connection) {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			LOG.error("Not able to perform transaction");
		}
	}

	public void endTransaction(Connection connection) {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			LOG.error("Not able to perform transaction");
			throw new TransactionException("Not able to perform transaction");
		}
	}

	public void commitTransaction(Connection connection) {
		try {
			connection.commit();
		} catch (SQLException e) {
			LOG.error("Not able to commit transaction");
			throw new TransactionException("Not able to commit transaction");
		}
	}
}
