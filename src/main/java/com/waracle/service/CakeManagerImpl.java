package com.waracle.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waracle.exception.CakeAlreadyExistException;
import com.waracle.model.Cake;
import com.waracle.repository.CakeRepository;
import com.warracle.converter.CakeConverter;
import com.warracle.dto.CakeDto;

@Service
public class CakeManagerImpl implements CakeManager {
	
	@Autowired
	CakeRepository cakeRepository;	
	
	public void addNewCake(CakeDto cakeDto) throws CakeAlreadyExistException {
		
		if(cakeRepository.findByName(cakeDto.getName()) != null) {
			throw new CakeAlreadyExistException("Cake: " + cakeDto.getName() + " already exists.");
		}
		Cake cake = CakeConverter.dtoToEntity(cakeDto);
		cakeRepository.save(cake);			
		
	}
	
	public List<CakeDto> findAllCakes() {
		List<Cake> cakes = (List<Cake>)cakeRepository.findAll();
		List<CakeDto> cakesDto = cakes.stream()
									  .map(cake->CakeConverter.entityToDto(cake))
									  .collect(Collectors.toList());
		return cakesDto;
	}
	
	public CakeDto findCakeByName(String name) {		
		Cake cake = cakeRepository.findByName(name);	
		return CakeConverter.entityToDto(cake);
	}
}
