package com.ces2.clase9.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("explorer")
public class Explorer {
	@Id 
	private Integer id;
	private String nickname;
	private Integer level;
	private String email;
	private Integer experience;

	@MappedCollection(idColumn = "explorer_id")
	private Set<ExplorerMission> missions = new HashSet<>();
}
