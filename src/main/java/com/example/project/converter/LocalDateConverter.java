package com.example.project.converter;

import javax.faces.convert.DateTimeConverter;

public class LocalDateConverter extends DateTimeConverter {

	public LocalDateConverter() {
		setType("localDate");
		setPattern("yyyy-MM-dd");
	}

}
