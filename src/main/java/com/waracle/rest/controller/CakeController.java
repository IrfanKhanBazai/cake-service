package com.waracle.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waracle.model.Cake;
import com.waracle.service.CakeManager;
//sdsdf
@RestController
public class CakeController {
	
	@Autowired
	CakeManager cakeManager;
	
	@RequestMapping(method = RequestMethod.GET,value = {"/","/cakes"})
    public List<Cake> findCakes() {     
	    return cakeManager.findAllCakes();
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/cakes/{name}")
    public Cake findCakeByName(@PathVariable String name) {     
	    return cakeManager.findCakeByName(name);
    }	
	
	@RequestMapping(method = RequestMethod.POST, value = "/cakes")
    public ResponseEntity<List<Cake>> addCakes(@RequestBody Cake cake) {     
		cakeManager.addNewCake(cake);
	    return ResponseEntity.ok(cakeManager.findAllCakes());    
	}

}
