package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

/**
 * Wrapper for the ThreadLocal holding the Connection to the database. It is a
 * singleton.
 * 
 * @author Neoware
 *
 */
@Repository
public class ConnectionThreadLocal {
	private static final Logger LOG = LoggerFactory.getLogger(ConnectionThreadLocal.class);
	public static ThreadLocal<Connection> connection = new ThreadLocal<>();
	@Autowired
	private DataSource dataSource;

	public ConnectionThreadLocal() {
	}

	/**
	 * Ask a connection to the datasource and set it in the ThreadLocal
	 */
	public void initConnection() {
		connection.set(DataSourceUtils.getConnection(dataSource));
	}

	/**
	 * Access the connection inside the ThreadLocal
	 * 
	 * @return the Java.sql connection
	 */
	public Connection getConnection() {
		return connection.get();
	}

	@Override
	protected void finalize() throws Throwable {
		DataSourceUtils.doCloseConnection(connection.get(), dataSource);
	}

	/**
	 * Close the connection inside the ThreadLocal making it available to be
	 * reuse by the datasource
	 */
	public void close() {
		try {
			LOG.info("Liberating connection");
			DataSourceUtils.doCloseConnection(connection.get(), dataSource);
		} catch (SQLException e) {
			LOG.error("Impossible to close the connection");
		}
	}
}
