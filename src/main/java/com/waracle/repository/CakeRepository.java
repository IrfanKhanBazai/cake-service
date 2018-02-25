package com.waracle.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waracle.model.Cake;

@Repository
public interface CakeRepository extends CrudRepository<Cake, Long> {
	Cake findByName(String name);
}
