package com.waracle.service;

import java.util.List;
import com.waracle.exception.CakeAlreadyExistException;
import com.warracle.dto.CakeDto;


public interface CakeManager {	
	public void addNewCake(CakeDto cake) throws CakeAlreadyExistException;	
	public List<CakeDto> findAllCakes() ;
	public CakeDto findCakeByName(String name) ;
}
