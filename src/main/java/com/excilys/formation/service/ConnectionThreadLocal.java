package com.excilys.formation.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.exception.TransactionException;
import com.excilys.formation.persistence.ConnectionManager;

public class ConnectionThreadLocal {
	private static final Logger LOG = LoggerFactory.getLogger(ConnectionThreadLocal.class);
	public static ThreadLocal<Connection> connection = new ThreadLocal<>();
	public static ConnectionThreadLocal instance = new ConnectionThreadLocal();

	public ConnectionThreadLocal() {
	}

	public void initConnection() {
		connection.set(ConnectionManager.getInstance().getConnection());
	}

	public Connection getConnection() {
		return connection.get();
	}

	public static ConnectionThreadLocal getInstance() {
		return instance;
	}

	public void beginTransaction() {
		try {
			connection.get().setAutoCommit(false);
		} catch (SQLException e) {
			LOG.error("Not able to begin transaction");
			throw new TransactionException("Not able to begin transaction");
		}
	}

	public void endTransaction() {
		try {
			connection.get().setAutoCommit(true);
		} catch (SQLException e) {
			LOG.error("Not able to finish transaction");
			throw new TransactionException("Not able to finish transaction");
		}
	}

	public void close() {
		try {
			connection.get().close();
		} catch (SQLException e) {
			LOG.error("Impossible to close the connection");
		}
	}
}
