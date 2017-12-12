package com.example.project;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class Spa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject @ManagedProperty("#{param.page}")
	private String page;

	@PostConstruct
	public void init() {
		if (page == null) {
			page = "page1";
		}
	}

	public void set(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

}