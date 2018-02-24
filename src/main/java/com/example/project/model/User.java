package com.example.project.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final int EMAIL_MAXLENGTH = 254;

	@Column(length = EMAIL_MAXLENGTH, nullable = false, unique = true)
	private @NotNull String email;

	@OneToOne(fetch = LAZY, cascade = ALL)
	private Credentials credentials;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

}
