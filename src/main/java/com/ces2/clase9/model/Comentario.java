package com.ces2.clase9.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Transient;

public class Comentario {
	
	private String name;
	private String content;
	private LocalDate published;
	private LocalDate updated;
	
	// one to many;
	// le llega el one
	@Transient // Se guarda como referencia, es el id del Anuncio.
	Anuncio anuncio;
	
	public Comentario() {
		super();
	}

	public Comentario(String name, String content, LocalDate published) {
		super();
		this.name = name;
		this.content = content;
		this.published = published;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getPublished() {
		return published;
	}

	public void setPublished(LocalDate published) {
		this.published = published;
	}

	public LocalDate getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDate updated) {
		this.updated = updated;
	}
	
}
