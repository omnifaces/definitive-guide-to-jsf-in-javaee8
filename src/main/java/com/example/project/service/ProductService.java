package com.example.project.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.project.model.Product;

@Stateless
public class ProductService {

	@PersistenceContext
	private EntityManager entityManager;

	@TransactionAttribute(REQUIRED)
	public Long persist(Product product) {
		entityManager.persist(product);
		return product.getId();
	}

	@TransactionAttribute(REQUIRED)
	public Product update(Product product) {
		return entityManager.merge(product);
	}

	@TransactionAttribute(SUPPORTS)
	public Product getById(Long id) {
		return entityManager.find(Product.class, id);
	}

	@TransactionAttribute(SUPPORTS)
	public List<Product> getAll() {
		return entityManager.createQuery("FROM Product", Product.class).getResultList();
	}

}
