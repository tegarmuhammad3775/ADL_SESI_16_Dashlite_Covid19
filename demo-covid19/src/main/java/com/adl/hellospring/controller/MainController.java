package com.adl.hellospring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.adl.hellospring.model.AllCovidInfo;
import com.adl.hellospring.services.CovidApiService;

@Controller
public class MainController {
	
	@Autowired
	CovidApiService covidService;
	
	@GetMapping("/")
	public String index(Model model){
		List<AllCovidInfo> listInfo = covidService.getAllCovidInfo();
		
		
		
		String totalCases = listInfo.get(0).getTotalCasesText();
		List<AllCovidInfo> topCases = new ArrayList<AllCovidInfo>();
		
		for (int i = 1 ; i <= 5; i++) {
			topCases.add(listInfo.get(i));
		}
				
		model.addAttribute("totalCases", totalCases);
		model.addAttribute("allCovidInfo", listInfo);
		model.addAttribute("topCases", topCases);
	    
	
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
































//@GetMapping("/")
//public String helloWorld(){
//	return "covid/src/html/index";
//}

