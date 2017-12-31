package com.waracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waracle.model.Cake;
import com.waracle.repository.CakeRepository;

@Service
public class CakeManagerImpl implements CakeManager {
	
	@Autowired
	CakeRepository cakeRepository;	
	
	public void addNewCake(Cake cake) {
		cakeRepository.save(cake);
	}
	
	public List<Cake> findAllCakes() {
		return cakeRepository.findAll();
	}
	
	public Cake findCakeByName(String name) {		
		return cakeRepository.findByName(name);	
	}
}
