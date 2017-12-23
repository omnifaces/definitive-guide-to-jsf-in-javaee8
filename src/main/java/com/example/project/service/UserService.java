package com.example.project.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean exist(String email) {
		return entityManager
			.createQuery("COUNT(u) FROM User u WHERE u.email = :email", Long.class)
			.setParameter("email", email)
			.getSingleResult() > 0;
	}

}
