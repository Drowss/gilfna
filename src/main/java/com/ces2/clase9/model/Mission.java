package com.ces2.clase9.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("missions")
public class Mission {
	@Id
	private Integer id;
	private String name;
	private String description;
	private Estado status;
	private Tipo type;
	private LocalDate initialDate;
	private LocalDate finishDate;
	
	// Many to Many
	@MappedCollection(idColumn = "mission_id")
	private Set<ExplorerMission> explorers = new HashSet<>();
	
}
