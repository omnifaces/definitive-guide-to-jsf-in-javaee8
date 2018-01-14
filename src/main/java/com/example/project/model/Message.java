package com.example.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import com.example.project.model.listener.MarkdownListener;

@Entity
@EntityListeners(MarkdownListener.class)
public class Message extends BaseEntity implements Markdown {

	private static final long serialVersionUID = 1L;

	@Lob
	@Column(nullable = false)
	private @NotNull String text;

	@Lob
	@Column(nullable = false)
	private String html;

	@Column(nullable = false, length = 8)
	private String version;

	@Override
	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (!text.equals(this.text)) {
			this.text = text;
			setVersion(null);
		}
	}

	public String getHtml() {
		return html;
	}

	@Override
	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

}
