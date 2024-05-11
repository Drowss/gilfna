package com.ces2.clase9.model;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("evento_autor")
public class AutorDTO {
	
	private Integer autor;
	private String name;

}
