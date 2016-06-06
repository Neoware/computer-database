package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Singleton class responsible for the created of connection to the database,
 * using hikariCP datasource
 * 
 * @author neoware
 *
 */
public class ConnectionManager {
	private static final Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);
	private static ConnectionManager instance = new ConnectionManager();
	private static HikariDataSource hikariDataSource;

	/**
	 * Setup of the hikariCP datasource using a properties file and some other
	 * configurations.
	 */
	private ConnectionManager() {
		HikariConfig config = new HikariConfig("/hikari.properties");
		config.setMaximumPoolSize(50);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		hikariDataSource = new HikariDataSource(config);
	}

	/**
	 * Return the unique instance of the class.
	 * 
	 * @return the singleton instance.
	 */
	public static ConnectionManager getInstance() {
		return instance;
	}

	/**
	 * Get a connection from the hikariCP datasource.
	 * 
	 * @return a connection to the database
	 */
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

	/**
	 * Helper function to close the ressources opened by querying the database.
	 * 
	 * @param connection
	 *            The connection that need to be closed.
	 * @param statement
	 *            The statement that need to be closed.
	 * @param resultSet
	 *            The resulset that need to be closed.
	 */
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

}
