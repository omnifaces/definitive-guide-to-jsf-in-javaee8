package com.example.project.service;

import static com.example.project.util.MessageDigests.digest;
import static com.example.project.util.MessageDigests.Algorithm.SHA_256;

import java.util.Arrays;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.example.project.model.Credentials;
import com.example.project.model.User;
import com.example.project.service.exception.DuplicateEntityException;
import com.example.project.service.exception.EntityNotFoundException;
import com.example.project.service.exception.InvalidPasswordException;
import com.example.project.service.exception.InvalidUsernameException;

@Stateless
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;

	public User getById(Long id) {
	    try {
	        return entityManager
	            .createQuery("FROM User u WHERE u.id = :id", User.class)
	            .setParameter("id", id)
	            .getSingleResult();
	    }
	    catch (NoResultException e) {
	        throw new EntityNotFoundException(e);
	    }
	}

	public Optional<User> findByEmail(String email) {
	    try {
	        return Optional.of(entityManager
	            .createQuery("FROM User u WHERE u.email = :email", User.class)
	            .setParameter("email", email)
	            .getSingleResult());
	    }
	    catch (NoResultException e) {
	        return Optional.empty();
	    }
	}

	public User getByEmailAndPassword(String email, String password) {
	    User user = findByEmail(email).orElseThrow(InvalidUsernameException::new);
	    Credentials credentials = user.getCredentials();
	    byte[] passwordHash = digest(password, credentials.getSalt(), SHA_256);

	    if (!Arrays.equals(passwordHash, credentials.getPasswordHash())) {
	        throw new InvalidPasswordException();
	    }

	    return user;
	}

	public Long create(User user) {
	    if (findByEmail(user.getEmail()).isPresent()) {
	        throw new DuplicateEntityException();
	    }

	    entityManager.persist(user);
	    return user.getId();
	}
}

