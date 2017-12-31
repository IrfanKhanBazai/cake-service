package com.waracle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waracle.model.Cake;

@Repository
public interface CakeRepository extends CrudRepository<Cake, Long> {

	List<Cake> findAll();
	Cake findByName(String name);
}
