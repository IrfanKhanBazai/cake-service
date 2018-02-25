package com.waracle.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waracle.exception.CakeAlreadyExistException;
import com.waracle.model.Cake;
import com.waracle.service.CakeManager;
import com.warracle.dto.CakeDto;

@RestController
@RequestMapping("/cake-manager")
public class CakeController {
	
	@Autowired
	CakeManager cakeManager;
	
	@RequestMapping(method = RequestMethod.GET,value = {"/cakes"})
    public List<CakeDto> findCakes() {     
	    return cakeManager.findAllCakes();
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/cakes/{name}")
    public CakeDto findCakeByName(@PathVariable String name) {     
	    return cakeManager.findCakeByName(name);
    }	
	
	@RequestMapping(method = RequestMethod.POST, value = "/cakes")
    public ResponseEntity<?> addCakes(@RequestBody CakeDto cakeDto) {     
		try {
			cakeManager.addNewCake(cakeDto);
			return ResponseEntity.ok(cakeManager.findAllCakes());
		}
		catch(CakeAlreadyExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	        
	}

}
