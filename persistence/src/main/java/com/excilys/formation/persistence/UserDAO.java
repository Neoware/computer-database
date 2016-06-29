package com.excilys.formation.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.excilys.formation.entity.User;

@Repository
public class UserDAO {

	@PersistenceContext
	EntityManager entityManager;

	public User findUserByUsername(String username) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
		TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
		User user = query.getSingleResult();
		return user;
	}

}
