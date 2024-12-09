package com.ces2.clase9.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enhancement {

	private String name;
	private String description;
	private Integer extraDamage;
	private Boolean droppable;
	private Integer levelRequirement;
	
	// one to many;
	// le llega el one
	@Column("weapon_id")
	private Integer weaponId;
	
}
