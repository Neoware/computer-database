package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static final String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String user = "admincdb";
	private static final String password = "qwerty1234";
	private static Connection connect;
	
	public static Connection getInstance() {
		if(connect == null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection(url, user, password);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}		
		return connect;	
	}
}
