package com.example.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Credentials extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private User user;

	@Column(length = 32)
	private @NotNull byte[] passwordHash;

	@Column(length = 40)
	private @NotNull byte[] salt;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

}