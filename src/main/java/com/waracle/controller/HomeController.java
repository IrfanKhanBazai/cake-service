package com.waracle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String defaultPage() {
		return "index";
	}
	
	@RequestMapping("/cakes")
	public String home() {
		System.out.print("dfadf");
		return "index";
	}
}
