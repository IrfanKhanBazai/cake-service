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

import com.waracle.exception.CakeAlreadyExistException;
import com.waracle.model.Cake;
import com.waracle.repository.CakeRepository;
import com.warracle.converter.CakeConverter;
import com.warracle.dto.CakeDto;

@RunWith(MockitoJUnitRunner.class)
@ComponentScan
public class CakeManagerImplTest {
	
	
	@InjectMocks
	CakeManagerImpl cakeManager;
	
	@Mock
	CakeRepository cakeRepository;	
	
	@Mock
	CakeConverter cakeConverter;
	
	@Test
	public void testfindCakeByName() {
		
		Mockito.when(cakeRepository.findByName("cheeseCake")).
							thenReturn(new Cake("cheeseCake","This is cheese cake","testurl"));
		
		CakeDto cakeDto = cakeManager.findCakeByName("cheeseCake");
		assertEquals(cakeDto.getName(),"cheeseCake");
		
	}
	
	
	@Test(expected = CakeAlreadyExistException.class) 
	public void testAddNewCakeThrowException() throws CakeAlreadyExistException {
		CakeDto cakeDto = new CakeDto("vanillaCake","This is vanilla cake","testurl");
		Cake cake = new Cake("vanillaCake","This is vanilla cake","testurl");
		
		Mockito.when(cakeRepository.findByName(cakeDto.getName())).thenReturn(cake);
		cakeManager.addNewCake(cakeDto);
		Mockito.verify(cakeRepository).save(cake);
	}
	
	@Test
	public void testfindAllCakes() {
		
		List<Cake> cakes = new ArrayList<>();
		cakes.add(new Cake("cheeseCake","This is cheese cake","testurl"));
		cakes.add(new Cake("vanillaCake","This is vanilla cake","testurl"));
		
		Mockito.when(cakeRepository.findAll()).thenReturn(cakes);
		
		
		List<CakeDto> cakesReturned = cakeManager.findAllCakes();
		assertEquals(cakesReturned.size(), 2);
		assertEquals(cakesReturned.get(0).getName(), "cheeseCake");
		assertEquals(cakesReturned.get(0).getDescription(), "This is cheese cake");
		assertEquals(cakesReturned.get(0).getImage(), "testurl");
		
		assertEquals(cakesReturned.get(1).getName(), "vanillaCake");
		assertEquals(cakesReturned.get(1).getDescription(), "This is vanilla cake");
		assertEquals(cakesReturned.get(1).getImage(), "testurl");
		
	}
	

}
