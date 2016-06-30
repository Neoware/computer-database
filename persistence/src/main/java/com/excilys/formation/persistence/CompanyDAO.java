package com.excilys.formation.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.formation.entity.Company;
import com.excilys.formation.exception.DaoException;

/**
 * DAO class for the company table.
 * 
 * @author neoware
 *
 */
@Repository
public class CompanyDAO {

	private static final Logger LOG = LoggerFactory.getLogger(CompanyDAO.class);
	@PersistenceContext
	EntityManager entityManager;

	public CompanyDAO() {
	}

	/**
	 * Get a row in the company table by id.
	 * 
	 * @param id
	 *            the id of the company that is searched.
	 * @return the company entity if the id exists, null otherwise.
	 * @throws DaoException
	 */
	public Company find(Long id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
		Root<Company> root = criteriaQuery.from(Company.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
		TypedQuery<Company> query = entityManager.createQuery(criteriaQuery);
		Company company = query.getSingleResult();
		return company;
	}

	/**
	 * Delete a company in the database and all the computers that have this
	 * company as company_id
	 * 
	 * @param id
	 *            the id of the company that will be deleted
	 * @throws DaoException
	 */
	public void delete(Long id) {
		Company company = find(id);
		if (company != null) {
			entityManager.remove(company);
		}
	}

	/**
	 * Get all company without a pagination system.
	 * 
	 * @return a list containing all companies from the database mapped on
	 *         entities.
	 * @throws DaoException
	 */
	public List<Company> getAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
		Root<Company> root = criteriaQuery.from(Company.class);
		criteriaQuery.select(root);
		TypedQuery<Company> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	/**
	 * Get a part of the company (useful for the pagination system)
	 * 
	 * @param offset
	 *            the offset of the request
	 * @param limit
	 *            the limit of the request
	 * @return a list of companies that resulted from the request done with
	 *         offset and limit.
	 * @throws DaoException
	 */
	public List<Company> getPage(PageRequest pageRequest) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
		Root<Company> root = criteriaQuery.from(Company.class);
		criteriaQuery.select(root);
		TypedQuery<Company> query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(pageRequest.getOffset()).setMaxResults(pageRequest.getLimit());
		return query.getResultList();
	}

	public int count() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Company> root = criteriaQuery.from(Company.class);
		criteriaQuery.select((criteriaBuilder.count(root)));
		int count = entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
		return count;
	}
}
