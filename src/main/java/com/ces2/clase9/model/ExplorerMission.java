package com.ces2.clase9.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("explorer_mission")
public class ExplorerMission {

	@Id
	private Integer id;
	@Column("explorer_id")
	private Integer explorerId;
	@Column("mission_id")
	private Integer missionId;
	private LocalDateTime assignedDate;
	private LocalDateTime finishedDate;

}
