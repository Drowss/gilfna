package com.ces2.clase9.repository;

import java.util.Set;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ces2.clase9.model.Evento;

public interface EventoRepositorio extends CrudRepository<Evento,Integer> {
	
	@Query("select e.* from evento e join evento_autor ea on e.id = ea.evento where ea.autor = :id")
	Set<Evento> findByAutorId(@Param("id") Integer id);
}
