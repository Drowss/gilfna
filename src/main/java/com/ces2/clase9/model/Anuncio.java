package com.ces2.clase9.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class Anuncio {
	
	@Id
	private Integer id;
	private String title;
	private String content;
	private LocalDateTime published;
	private LocalDateTime updated;
	
	// one to one
	private AggregateReference<Autor,Integer> autorid;
	
	// one to many
	// le llega el muchos
	private Set<Comentario> comentarios = new HashSet<>();
	// one to many;
	// le llega el one
	@Transient // Se guarda como referencia, es el id del Anuncio.
	Anuncio anuncio;
	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	public void addComentario(Comentario comentario) {
		comentarios.add(comentario);
		comentario.anuncio = this;
	}

	public AggregateReference<Autor, Integer> getAutor() {
		return autorid;
	}

	public void setAutor(AggregateReference<Autor, Integer> autorid) {
		this.autorid = autorid;
	}
	
	public Anuncio() {}

	public Anuncio(String title, String content, LocalDateTime published,
			AggregateReference<Autor,Integer> autorid) {
		super();
		this.title = title;
		this.content = content;
		this.published = published;
		this.autorid = autorid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getPublished() {
		return published;
	}

	public void setPublished(LocalDateTime published) {
		this.published = published;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", title=" + title + ", content=" + content + ", published=" + published
				+ ", updated=" + updated + "]";
	}
	
	
	
}
