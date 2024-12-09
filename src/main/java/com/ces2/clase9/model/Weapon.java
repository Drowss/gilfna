package com.ces2.clase9.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("weapons")
public class Weapon {
	
	@Id
	private Integer id;
	private Integer baseDamage;
	private String description;
	private LocalDateTime forgedDate;
	private Boolean droppable;
	
	// one to one
	private AggregateReference<Explorer,Integer> explorerId;
	
	// one to many
	// le llega el muchos
	@MappedCollection(idColumn = "weapon_id")
	private Set<Enhancement> enhancements = new HashSet<>();

}
