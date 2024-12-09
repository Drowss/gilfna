package com.ces2.clase9.repository;

import java.util.List;
import java.util.Set;

import com.ces2.clase9.model.Mission;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends CrudRepository<Mission,Integer> {
	
	@Query("select e.* from evento e join evento_autor ea on e.id = ea.evento where ea.autor = :id")
	Set<Mission> findByAutorId(@Param("id") Integer id);

	@Query("SELECT m.* FROM missions m " +
			"JOIN explorer_mission em ON m.id = em.mission_id " +
			"WHERE em.explorer_id = :explorerId")
	List<Mission> findMissionsByExplorerId(@Param("explorerId") Integer explorerId);
}
