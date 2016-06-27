package com.excilys.formation.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.util.DateUtils;

public class ComputerRowMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Company company = new Company(resultSet.getLong("company_id"), resultSet.getString("company_name"));
		Computer computer = Computer.getBuilder().name(resultSet.getString("name")).id(resultSet.getLong("id"))
				.introduced(DateUtils.timestampToLocalDate(resultSet.getTimestamp("introduced")))
				.discontinued(DateUtils.timestampToLocalDate(resultSet.getTimestamp("discontinued")))
				.computerCompany(company).build();
		return computer;
	}

}
