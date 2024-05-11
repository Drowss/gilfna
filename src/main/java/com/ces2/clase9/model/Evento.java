package com.ces2.clase9.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("evento")
public class Evento {
	@Id
	private Integer id;
	private String title;
	private String descripcion;
	private Estado estado;
	private Tipo tipo;
	private LocalDate datecreated;
	private LocalDate dateupdated;
	
	// Many to Many
	private Set<AutorDTO> autores = new HashSet<>();
	
	public void addAutor(Autor autor) {
		AutorDTO autorDto = new AutorDTO();
		autorDto.setAutor(autor.getId());
		autorDto.setName(autor.getName()+" "+autor.getLastname());
		this.autores.add(autorDto);
	}
	
	public Set<Integer> getAutorIds(){
		return this.autores.stream()
				.map(AutorDTO::getAutor)
				.collect(Collectors.toSet());
	}
	
	public Set<String> getAutorNames(){
		return this.autores.stream()
				.map(AutorDTO::getName)
				.collect(Collectors.toSet());
	}
	
}
