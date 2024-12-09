package com.ces2.clase9.repository;

import org.springframework.data.repository.CrudRepository;

import com.ces2.clase9.model.Weapon;

import java.util.Optional;

public interface WeaponRepository extends CrudRepository<Weapon,Integer>{
    Optional<Weapon> findByExplorerId(Integer explorerId);
    Optional<Weapon> findById(Integer id);
}
