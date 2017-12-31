package com.waracle.rest.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import  com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import com.waracle.model.Cake;
import com.waracle.service.CakeManager;

public class CakeControllerTest {
	
	private MockMvc mockMvc;   
    
    @InjectMocks
    private CakeController cakeController;

    @Mock
    private CakeManager cakeManager;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cakeController)
                .build();
    }   
    
    @Test
    public void testfindAllCakes() throws Exception {
        List<Cake> cakes = Arrays.asList(
        		new Cake("cheeseCake","This is cheese cake","testurl"),
        		new Cake("vanillaCake","This is vanilla cake","testurl"));
        when(cakeManager.findAllCakes()).thenReturn(cakes);
        mockMvc.perform(get("/cakes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("cheeseCake")))
                .andExpect(jsonPath("$[0].description", is("This is cheese cake")))
                .andExpect(jsonPath("$[1].name", is("vanillaCake")))
                .andExpect(jsonPath("$[1].description", is("This is vanilla cake")));
        verify(cakeManager, times(1)).findAllCakes();
        verifyNoMoreInteractions(cakeManager);
    }  
    
    @Test
    public void testfindCakeByName() throws Exception {
    	Cake cake = new Cake("cheeseCake","This is cheese cake","testurl");
        when(cakeManager.findCakeByName("cheeseCake")).thenReturn(cake);
        mockMvc.perform(get("/cakes/cheeseCake"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("name", is("cheeseCake")))
                .andExpect(jsonPath("description", is("This is cheese cake")));
              
        verify(cakeManager, times(1)).findCakeByName("cheeseCake");
        verifyNoMoreInteractions(cakeManager);
    }
	
	@Test
	public void testAddCakes() throws Exception {
	    Cake cake =  new Cake("cheeseCake","This is cheese cake","testurl");
        when(cakeManager.findAllCakes()).thenReturn(Arrays.asList(cake));
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(cake);
        
        mockMvc.perform(post("/cakes")
         .contentType(MediaType.APPLICATION_JSON)
         .content(jsonInString))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is("cheeseCake")))
        .andExpect(jsonPath("$[0].description", is("This is cheese cake")));
	 
	}


}
