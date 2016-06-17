package com.excilys.formation.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.entity.Company;

public class CompanyRowMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Company company = new Company(resultSet.getLong("id"), resultSet.getString("name"));
		return company;
	}

}
