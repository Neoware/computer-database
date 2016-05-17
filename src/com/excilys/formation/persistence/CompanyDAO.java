package com.excilys.formation.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.crypto.provider.RSACipher;

public class CompanyDAO extends DAO<Company>{

	@Override
	public Company find(int id) {
		try {
			statement = connection.createStatement();
			String sql = "SELECT * FROM company WHERE id = " + id;
			ResultSet result = statement.executeQuery(sql);
			if (result.first()){
				Company company = new Company(id, result.getString("name"));
				return company;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public Company create(Company toCreate) {
		try {
			statement = connection.createStatement();
			String sql = "INSERT INTO computer VALUES (NULL, \""+ toCreate.getName() + "\")";
			System.out.println(sql);
			statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Company update(Company toUpdate) {
		try {
			statement = connection.createStatement();
			String sql = "UPDATE computer SET name=\""+ toUpdate.getName() + "\" WHERE id=" + toUpdate.getId();
			System.out.println(sql);
			statement.executeQuery(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Company delete(int id) {
		try {
			statement = connection.createStatement();
			String sql = "DELETE from computer where id=" + id;
			System.out.println(sql);
			statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Company> getAll() {
		try {
			statement = connection.createStatement();
			String sql = "SELECT * from company";
			ResultSet result = statement.executeQuery(sql);
			List<Company> companies = new ArrayList<>();
			while(result.next()) {
					Company temp = new Company();
					temp.setId(result.getInt("id"));
					temp.setName(result.getString("name"));
					companies.add(temp);
				} 
			return companies;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
