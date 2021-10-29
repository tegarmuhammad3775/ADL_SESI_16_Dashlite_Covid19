package com.adl.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String helloWorld(){
		return "covid/src/html/index";
	}
	
	@GetMapping("/index.html")
	public String indexPage(){
		return "covid/src/html/index";
	}
	
	@GetMapping("/html/index.html")
	public String homePage(){
		return "covid/src/html/index";
	}
	
	@GetMapping("html/countries.html")
	public String countriesPage(){
		return "covid/src/html/countries.html";
	}
	
	@GetMapping("html/symptoms.html")
	public String symptomsPage(){
		return "covid/src/html/symptoms.html";
	}
	
	@GetMapping("html/prevention.html")
	public String preventionPage(){
		return "covid/src/html/prevention.html";
	}
	
	@GetMapping("html/faqs.html")
	public String faqsPage(){
		return "covid/src/html/faqs.html";
	}
	
	@GetMapping("html/components/index.html")
	public String componentsPage(){
		return "covid/src/html/components/index.html";
	}
	
	
	
	
	
	
	
	
	
	

}
