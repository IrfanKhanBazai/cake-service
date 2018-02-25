package com.waracle.rest.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.service.CakeManager;
import com.warracle.dto.CakeDto;

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
        List<CakeDto> cakesDto = Arrays.asList(
        		new CakeDto("cheeseCake","This is cheese cake","testurl"),
        		new CakeDto("vanillaCake","This is vanilla cake","testurl"));
        when(cakeManager.findAllCakes()).thenReturn(cakesDto);
        mockMvc.perform(get("/cake-manager/cakes"))
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
    	CakeDto cakeDto = new CakeDto("cheeseCake","This is cheese cake","testurl");
        when(cakeManager.findCakeByName("cheeseCake")).thenReturn(cakeDto);
        mockMvc.perform(get("/cake-manager/cakes/cheeseCake"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("name", is("cheeseCake")))
                .andExpect(jsonPath("description", is("This is cheese cake")));
              
        verify(cakeManager, times(1)).findCakeByName("cheeseCake");
        verifyNoMoreInteractions(cakeManager);
    }
	
	@Test
	public void testAddCakes() throws Exception {
	    CakeDto cakeDto =  new CakeDto("cheeseCake","This is cheese cake","testurl");
        when(cakeManager.findAllCakes()).thenReturn(Arrays.asList(cakeDto));
       // doThrow(new CakeAlreadyExistException("exception")).when(cakeManager).addNewCake(cakeDto);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(cakeDto);
        
        mockMvc.perform(post("/cake-manager/cakes")
         .contentType(MediaType.APPLICATION_JSON)
         .content(jsonInString))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is("cheeseCake")))
        .andExpect(jsonPath("$[0].description", is("This is cheese cake")));
  
	 
	}
	


}
