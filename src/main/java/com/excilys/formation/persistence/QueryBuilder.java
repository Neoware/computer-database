package com.excilys.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.exception.DaoException;
import com.excilys.formation.service.PageRequest;
import com.excilys.formation.util.StringUtils;

public class QueryBuilder {
	private static final Logger LOG = LoggerFactory.getLogger(QueryBuilder.class);
	private StringBuilder stringBuilder;
	private static Map<String, String> equivalence;

	static {
		equivalence = new HashMap<>();
		equivalence.put("introduced", "computer.introduced");
		equivalence.put("discontinued", "computer.discontinued");
		equivalence.put("companyName", "company.name");
		equivalence.put("name", "computer.name");
	}

	public QueryBuilder() {
		stringBuilder = new StringBuilder();
	}

	public PreparedStatement createGetPageQuery(PageRequest pageRequest, Connection connection) {
		int i = 0;
		Map<String, Integer> parameters = new HashMap<>();
		stringBuilder
				.append("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, "
						+ "company.name AS company_name FROM computer "
						+ "LEFT JOIN company ON computer.company_id = company.id ");
		if (!StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
			stringBuilder.append("WHERE company.name LIKE ? OR computer.name LIKE ? ");
			parameters.put("searchCompany", ++i);
			parameters.put("searchComputer", ++i);
		}
		if (!StringUtils.isNullOrEmpty(pageRequest.getSort())) {
			stringBuilder.append("ORDER BY " + equivalence.get(pageRequest.getSort()) + " ");
		}
		stringBuilder.append("LIMIT ?, ? ");
		parameters.put("offset", ++i);
		parameters.put("limit", ++i);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			String search = pageRequest.getSearch() + '%';
			if (!StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
				preparedStatement.setString(parameters.get("searchCompany"), search);
				preparedStatement.setString(parameters.get("searchComputer"), search);
			}
			preparedStatement.setInt(parameters.get("offset"), pageRequest.getOffset());
			preparedStatement.setInt(parameters.get("limit"), pageRequest.getLimit());
		} catch (SQLException e) {
			LOG.error("Error while building query to create a page");
			throw new DaoException("Error while building query to create a page");
		}
		LOG.info(preparedStatement.toString());
		return preparedStatement;
	}

	public PreparedStatement createGetCountQuery(PageRequest pageRequest, Connection connection) {
		stringBuilder.append("SELECT COUNT( * ) FROM computer LEFT JOIN company ON computer.company_id = company.id ");
		PreparedStatement preparedStatement = null;
		if (!StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
			stringBuilder.append("WHERE company.name LIKE ? OR computer.name LIKE ? ");
		}
		try {
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			if (!StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
				String search = pageRequest.getSearch() + '%';
				preparedStatement.setString(1, search);
				preparedStatement.setString(2, search);
			}
		} catch (SQLException e) {
			LOG.error("Error while building query to count elements");
			throw new DaoException("Error while building query to count a page");
		}
		LOG.info(preparedStatement.toString());
		return preparedStatement;
	}
}
