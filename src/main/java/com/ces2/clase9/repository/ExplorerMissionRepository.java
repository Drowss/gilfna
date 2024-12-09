package com.ces2.clase9.repository;

import com.ces2.clase9.model.ExplorerMission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExplorerMissionRepository extends CrudRepository<ExplorerMission,Integer> {
    List<ExplorerMission> findByMissionId(Integer missionId);
}
