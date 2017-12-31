package com.waracle.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.Assert.*;

import com.waracle.model.Cake;
import com.waracle.repository.CakeRepository;

@RunWith(MockitoJUnitRunner.class)
@ComponentScan
public class CakeManagerImplTest {
	
	
	@InjectMocks
	CakeManagerImpl cakeManager;
	
	@Mock
	CakeRepository cakeRepository;	
	
	@Test
	public void testfindCakeByName() {
		
		Mockito.when(cakeRepository.findByName("cheeseCake")).
							thenReturn(new Cake("cheeseCake","This is cheese cake","testurl"));
		
		Cake cake = cakeManager.findCakeByName("cheeseCake");
		assertEquals(cake.getName(),"cheeseCake");
		
	}
	
	@Test
	public void testAddNewCake() {
		Cake cake = new Cake("vanillaCake","This is vanilla cake","testurl");
		
		cakeManager.addNewCake(cake);
		Mockito.verify(cakeRepository).save(cake);
	}
	
	@Test
	public void testfindAllCakes() {
		
		List<Cake> cakes = new ArrayList<>();
		cakes.add(new Cake("cheeseCake","This is cheese cake","testurl"));
		cakes.add(new Cake("vanillaCake","This is vanilla cake","testurl"));
		
		Mockito.when(cakeRepository.findAll()).thenReturn(cakes);
		
		
		List<Cake> cakesReturned = cakeManager.findAllCakes();
		assertEquals(cakesReturned.size(), 2);
		assertEquals(cakesReturned.get(0).getName(), "cheeseCake");
		assertEquals(cakesReturned.get(0).getDescription(), "This is cheese cake");
		assertEquals(cakesReturned.get(0).getImage(), "testurl");
		
		assertEquals(cakesReturned.get(1).getName(), "vanillaCake");
		assertEquals(cakesReturned.get(1).getDescription(), "This is vanilla cake");
		assertEquals(cakesReturned.get(1).getImage(), "testurl");
		
	}
	

}
