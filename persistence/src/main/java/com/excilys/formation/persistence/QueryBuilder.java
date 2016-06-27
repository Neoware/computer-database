package com.excilys.formation.persistence;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.exception.DaoException;
import com.excilys.formation.persistence.PageRequest;
import com.excilys.formation.util.StringUtils;

/**
 * Class building queries from informations contained in the pageRequest object
 * 
 * @author neoware
 *
 */
public class QueryBuilder {
	private static final Logger LOG = LoggerFactory.getLogger(QueryBuilder.class);
	private StringBuilder stringBuilder;
	private static Map<String, String> equivalence;

	/**
	 * Setting equivalence between the POST sort variables and the SQL ones.
	 */
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

	/**
	 * Create a page query that will retrieved a part of the computers by using
	 * clauses contained in the pageRequest object
	 * 
	 * @param pageRequest
	 *            Object containing clauses that will be used in the SQL
	 *            request.
	 * @param connection
	 *            The database connection that will be used.
	 * @return the prepared statement that has been generated.
	 * @throws DaoException
	 */
	public String createGetPageQuery(PageRequest pageRequest) throws DaoException {
		stringBuilder
				.append("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, "
						+ "company.name AS company_name FROM computer "
						+ "LEFT JOIN company ON computer.company_id = company.id ");
		if (!StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
			stringBuilder.append("WHERE company.name LIKE \"" + pageRequest.getSearch() + "%\" OR computer.name LIKE \""
					+ pageRequest.getSearch() + "%\" ");
		}
		if (!StringUtils.isNullOrEmpty(pageRequest.getSort())) {
			stringBuilder.append("ORDER BY " + equivalence.get(pageRequest.getSort()) + " ");
			if (!StringUtils.isNullOrEmpty(pageRequest.getOrder())) {
				if (pageRequest.getOrder().equals("ASC")) {
					stringBuilder.append(" ASC ");
				} else if (pageRequest.getOrder().equals("DESC")) {
					stringBuilder.append(" DESC ");
				}
			}
		}
		stringBuilder.append("LIMIT " + pageRequest.getOffset() + ", " + pageRequest.getLimit());
		return stringBuilder.toString();
	}

	/**
	 * Get the count of a request generated from pagerequest Object.
	 * 
	 * @param pageRequest
	 *            The object containing clauses that will be used in the
	 *            request.
	 * @param connection
	 *            The connection to the database that will be used.
	 * @return The prepared statement that has been generated.
	 * @throws DaoException
	 */
	public String createGetCountQuery(PageRequest pageRequest) throws DaoException {
		stringBuilder.append("SELECT COUNT( * ) FROM computer LEFT JOIN company ON computer.company_id = company.id ");
		if (!StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
			stringBuilder.append("WHERE company.name LIKE \"" + pageRequest.getSearch() + "%\" OR computer.name LIKE \""
					+ pageRequest.getSearch() + "%\" ");
		}
		return stringBuilder.toString();
	}
}
