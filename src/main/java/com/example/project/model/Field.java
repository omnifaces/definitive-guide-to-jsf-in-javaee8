package com.example.project.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Inheritance
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "formId", "name" }) })
public abstract class Field extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 32, nullable = false)
	private String formId;

	@Column(length = 32, nullable = false)
	private String name;

	@Column(length = 64, nullable = false)
	private String label;

	@Column(nullable = false)
	private boolean required = false;

	@Column(nullable = false)
	private int ordering;

	public static <F extends Field> F create(int ordering, Class<F> type, String formId, String name, String label) {
		return create(ordering, type, formId, name, label, false);
	}

	public static <F extends Field> F create(int ordering, Class<F> type, String formId, String name, String label, boolean required) {
		try {
			F field = type.newInstance();
			field.setFormId(formId);
			field.setName(name);
			field.setLabel(label);
			field.setRequired(required);
			field.setOrdering(ordering);
			return field;
		}
		catch (InstantiationException | IllegalAccessException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public abstract void populate(DynamicForm form);

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public int getOrdering() {
		return ordering;
	}

	public interface DynamicForm {
		void createOutputLabel(Field field);
		void createInputText(Field field);
		void createInputSecret(Field field);
		void createCommandButton(Field field);
		void createMessage(Field field);
	}

}
