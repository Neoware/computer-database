package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.exception.TransactionException;
import com.excilys.formation.persistence.ConnectionManager;

/**
 * Wrapper for the ThreadLocal holding the Connection to the database. It is a
 * singleton.
 * 
 * @author Neoware
 *
 */
public class ConnectionThreadLocal {
	private static final Logger LOG = LoggerFactory.getLogger(ConnectionThreadLocal.class);
	public static ThreadLocal<Connection> connection = new ThreadLocal<>();
	public static ConnectionThreadLocal instance = new ConnectionThreadLocal();

	private ConnectionThreadLocal() {
	}

	/**
	 * Ask a connection to the datasource and set it in the ThreadLocal
	 */
	public void initConnection() {
		connection.set(ConnectionManager.getInstance().getConnection());
	}

	/**
	 * Access the connection inside the ThreadLocal
	 * 
	 * @return the Java.sql connection
	 */
	public Connection getConnection() {
		return connection.get();
	}

	/**
	 * 
	 * @return the instance of this singleton
	 */
	public static ConnectionThreadLocal getInstance() {
		return instance;
	}

	/**
	 * Initialize a transaction process by setting the connection inside the
	 * ThreadLocal
	 */
	public void beginTransaction() {
		try {
			connection.get().setAutoCommit(false);
		} catch (SQLException e) {
			LOG.error("Not able to begin transaction");
			throw new TransactionException("Not able to begin transaction");
		}
	}

	/**
	 * End of a transaction process by setting the connection inside the
	 * ThreadLocal
	 */
	public void endTransaction() {
		try {
			connection.get().setAutoCommit(true);
		} catch (SQLException e) {
			LOG.error("Not able to finish transaction");
			throw new TransactionException("Not able to finish transaction");
		}
	}

	/**
	 * Close the connection inside the ThreadLocal making it available to be reuse by the datasource
	 */
	public void close() {
		try {
			LOG.info("Liberating connection");
			connection.get().close();
		} catch (SQLException e) {
			LOG.error("Impossible to close the connection");
		}
	}

	/**
	 * commit queries when transaction mode is on
	 */
	public void commit() {
		try {
			connection.get().commit();
		} catch (SQLException e) {
			LOG.error("Impossible to commit the transaction", e);
		}
	}
}
