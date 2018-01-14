package com.example.project.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.project.model.Message;

@Stateless
public class MessageService {

	@PersistenceContext
	private EntityManager entityManager;

	@TransactionAttribute(REQUIRED)
	public Long persist(Message message) {
		entityManager.persist(message);
		return message.getId();
	}

	@TransactionAttribute(REQUIRED) // Transaction is required in order to get JPA to auto-flush the by MarkdownListener auto-updated entities when Markdown version has changed.
	public List<Message> list() {
		return entityManager.createQuery("FROM Message ORDER BY id DESC", Message.class).getResultList();
	}

}
