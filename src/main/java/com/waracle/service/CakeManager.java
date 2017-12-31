package com.waracle.service;


import java.util.List;
import com.waracle.model.Cake;


public interface CakeManager {	
	public void addNewCake(Cake cake);	
	public List<Cake> findAllCakes() ;
	public Cake findCakeByName(String name) ;
}
