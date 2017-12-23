package com.example.project.model;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
