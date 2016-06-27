package com.excilys.formation.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.exception.DaoException;
import com.excilys.formation.service.Cache;
import com.excilys.formation.service.PageRequest;
import com.excilys.formation.util.StringUtils;

/**
 * DAO class for the computer table.
 * 
 * @author neoware
 *
 */
@Repository
public class ComputerDAO {

	private static final Logger LOG = LoggerFactory.getLogger(ComputerDAO.class);
	@Autowired
	private Cache cache;
	@PersistenceContext
	EntityManager entityManager;

	public ComputerDAO() {
	}

	/**
	 * Get an entity corresponding to a row in the computer table.
	 * 
	 * @param id
	 *            the id of the computer that will be searched.
	 * @return the computer entity if the id exists, null otherwise.
	 * @throws DaoException
	 */
	public Computer find(Long id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
		TypedQuery<Computer> query = entityManager.createQuery(criteriaQuery);
		Computer computer = query.getSingleResult();
		return computer;
	}

	/**
	 * Create a new computer.
	 *
	 * @param toCreate
	 *            The computer entity corresponding to the row that will be
	 *            inserted.
	 * @return The computer entity that has been inserted.
	 * @throws DaoException
	 */
	public Computer create(Computer toCreate) {
		entityManager.persist(toCreate);
		return toCreate;
	}

	/**
	 * Update an already existing computer.
	 *
	 * @param toUpdate
	 *            Entity containing all attributes that will be updated in the
	 *            database.
	 * @throws DaoException
	 */
	public void update(Computer toUpdate) {
		Computer computer = find(toUpdate.getId());
		if (computer != null) {
			computer.setName(toUpdate.getName());
			computer.setIntroduced(toUpdate.getIntroduced());
			computer.setDiscontinued(toUpdate.getDiscontinued());
			computer.setComputerCompany(toUpdate.getComputerCompany());
		}
	}

	/**
	 * Delete a computer in the database
	 *
	 * @param id
	 *            The id of the computer that will be deleted.
	 * @throws DaoException
	 */
	public void delete(Long id) {
		Computer computer = find(id);
		if (computer != null) {
			entityManager.remove(computer);
		}
	}

	/**
	 * Get a page from the database by building a complex request from a
	 * Pagerequest object.
	 *
	 * @param pageRequest
	 *            the object containg all the parameter to build the SQL request
	 *            like offset, limit, where clause.
	 * @return The list of computers corresponding to the result of the query
	 *         that has been performed.
	 * @throws DaoException
	 */
	public List<Computer> getPage(PageRequest pageRequest) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);
		Join<Company, Computer> join = root.join("computerCompany", JoinType.LEFT);
		criteriaQuery.select(root);
		if (!StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
			Predicate likePredicate = criteriaBuilder.like(root.get("name"), pageRequest.getSearch() + "%");
			criteriaQuery.where(likePredicate);
		}
		if (!StringUtils.isNullOrEmpty(pageRequest.getSort())) {
			if (!StringUtils.isNullOrEmpty(pageRequest.getOrder())) {
				if (pageRequest.getOrder().equals("ASC")) {
					if (!pageRequest.getSort().equals("companyName")) {
						criteriaQuery.orderBy(criteriaBuilder.asc(root.get(pageRequest.getSort())));
					} else {
						criteriaQuery.orderBy(criteriaBuilder.asc(join.get("name")));
					}
				} else if (pageRequest.getOrder().equals("DESC")) {
					if (!pageRequest.getSort().equals("companyName")) {
						criteriaQuery.orderBy(criteriaBuilder.desc(root.get(pageRequest.getSort())));
					} else {
						criteriaQuery.orderBy(criteriaBuilder.desc(join.get("name")));
					}
				}
			}
		}
		TypedQuery<Computer> query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(pageRequest.getOffset()).setMaxResults(pageRequest.getLimit());
		return query.getResultList();
	}

	/**
	 * Retrieve the count of the query build from the pagerequest object without
	 * the limit and offset clause.
	 *
	 * @param pageRequest
	 *            the object containg all the parameter to build the SQL request
	 *            like where clause.
	 * @return the count that has been retrieved.
	 * @throws DaoException
	 */
	public int getCountElements(PageRequest pageRequest) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select((criteriaBuilder.countDistinct(root)));
		if (!StringUtils.isNullOrEmpty(pageRequest.getSearch())) {
			Predicate likePredicate = criteriaBuilder.like(root.get("name"), pageRequest.getSearch() + "%");
			criteriaQuery.where(likePredicate);
		}
		return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
	}

	/**
	 * Delete all computers that have a specific company as company_id entry.
	 * Useful for the delete company feature.
	 *
	 * @param companyId
	 *            The id of the company corresponding to the computers that will
	 *            be deleted.
	 * @throws DaoException
	 */
	public void deleteByCompany(Long companyId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("company_id"), companyId));
		TypedQuery<Computer> query = entityManager.createQuery(criteriaQuery);
		List<Computer> computers = query.getResultList();
		for (Computer computer : computers) {
			entityManager.remove(computer);
		}
	}

	/**
	 * Count the total number of computers in the database.
	 *
	 * @return the count retrieved from the database.
	 * @throws DaoException
	 */
	public int count() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Computer> root = criteriaQuery.from(Computer.class);
		criteriaQuery.select((criteriaBuilder.count(root)));
		int count = entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
		return count;
	}
}
