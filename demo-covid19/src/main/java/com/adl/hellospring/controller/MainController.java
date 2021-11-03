package com.adl.hellospring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adl.hellospring.model.AllCovidInfo;
import com.adl.hellospring.model.FaqsModel;
import com.adl.hellospring.model.MockPostModel;
import com.adl.hellospring.model.PreventionModel;
import com.adl.hellospring.repository.FaqsRepository;
import com.adl.hellospring.repository.PreventionRepository;
import com.adl.hellospring.services.CovidApiService;
import com.adl.hellospring.services.MockPostService;
import com.adl.hellospring.utility.FileUtility;

@Controller
public class MainController {
	
	@Autowired
	CovidApiService covidService;
	
	@Autowired
	MockPostService mockService;
	
	@Autowired
	PreventionRepository preventionRepo;
	
	@Autowired
	FaqsRepository faqsRepo;
	
	@GetMapping("/")
	public String index(@RequestParam(value="country", defaultValue = "") String country, Model model){
		AllCovidInfo covid = new AllCovidInfo();
		if(country.equalsIgnoreCase("")) {
			covid = covidService.getCovidInfo("world");
		}
		else{
			covid = covidService.getCovidInfo(country);
		}
		
		List<AllCovidInfo> listInfo = covidService.getAllCovidInfo();
		String totalCases = listInfo.get(0).getTotalCasesText();
		List<AllCovidInfo> topCases = new ArrayList<AllCovidInfo>();
				
		model.addAttribute("totalCases", totalCases);
		model.addAttribute("allCovidInfo", listInfo);
		model.addAttribute("topCases", topCases);
		model.addAttribute("covidSearch", covid);
		model.addAttribute("mockModel", new MockPostModel());
	    
	
		return "covid/src/html/index";
	}
	
	@PostMapping("/")
	public String inputModel(@ModelAttribute("mockModel") MockPostModel data){
		MockPostModel response = mockService.inputModel(data);
		return "redirect:/";
	}

	@GetMapping("/{country}")
	public String cuuntries(@ModelAttribute("allCovidInfo") List<AllCovidInfo> listInfo, @PathVariable(value="country") String country, Model model){
		
		List<AllCovidInfo> listCovidInfo = listInfo;
		model.addAttribute("listCovidInfo");
		return "index";
	}
	
	@GetMapping("/input")
	public String inputPreventionPage(){
		return "covid/src/html/input_prevention";
	}
	
	
	@GetMapping("/index.html")
	public String indexPage(){
		return "covid/src/html/index.html";
	}
	
	@GetMapping("/index")
	public String indexPage2(){
		return "covid/src/html/index.html";
	}
	
	@GetMapping("html/index.html")
	public String homePage(){
		return "covid/src/html/index.html";
	}
	
	@GetMapping("html/countries.html")
	public String countriesPage(){
		return "covid/src/html/countries.html";
	}
	
	@GetMapping("countries")
	public String countriesPage2(){
		return "covid/src/html/countries.html";
	}
	
	@GetMapping("html/symptoms.html")
	public String symptomsPage(){
		return "covid/src/html/symptoms.html";
	}
	
	@GetMapping("/symptoms")
	public String symptomsPage2(){
		return "covid/src/html/symptoms.html";
	}
	
	@GetMapping("html/prevention.html")
	public String preventionPage(Model model){
		
		List<PreventionModel> listPrevention = preventionRepo.findAll();
		model.addAttribute("listPrevention", listPrevention);
		
		return "covid/src/html/prevention.html";
	}
	
	@GetMapping("/prevention")
	public String preventionPage2(Model model){
		
		List<PreventionModel> listPrevention = preventionRepo.findAll();
		model.addAttribute("listPrevention", listPrevention);
		
		return "covid/src/html/prevention.html";
	}
	
	@GetMapping("html/faqs.html")
	public String faqsPage(Model model){
		
		List<FaqsModel> listFaqs = faqsRepo.findAll();
		model.addAttribute("listFaqs", listFaqs);
		
		return "covid/src/html/faqs.html";
	}
	
	@GetMapping("/faqs")
	public String faqsPage2(Model model){
		
		List<FaqsModel> listFaqs = faqsRepo.findAll();
		model.addAttribute("listFaqs", listFaqs);
		
		return "covid/src/html/faqs.html";
	}
	
	@GetMapping("html/components/index.html")
	public String componentsPage(){
		return "covid/src/html/components/index.html";
	}
	
	@GetMapping("components")
	public String componentsPage2(){
		return "covid/src/html/components/index.html";
	}
	
	@GetMapping("/prevention/input")
	public String getInputPrevention(Model model) {
		
		model.addAttribute("preventionModel", new PreventionModel());
		return "covid/src/html/input_prevention.html";
	}
	
	@GetMapping("/faqs/input")
	public String getInputFaqs(Model model) {
		
		model.addAttribute("faqsModel", new FaqsModel());
		return "covid/src/html/input_faqs.html";
	}
	
	@PostMapping("/prevention/input")
	public String inputPrevention(
			@ModelAttribute("preventionModel") PreventionModel data,
			Model model,
			@RequestParam("file") MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String type = file.getContentType();
		if(type.equalsIgnoreCase("image/png") || type.equalsIgnoreCase("image/jpeg")){
			String uploadDir = "prevention-image/";
			
			FileUtility.saveFile(file, uploadDir, fileName);
			data.setImage(fileName);
			this.preventionRepo.save(data);
		}else {
			System.out.println("Bukan format yang benar");
		}
		
		
		return "redirect:/prevention/input";
	}
	
	@PostMapping("/faqs/input")
	public String inputFaqs(
			@ModelAttribute("faqsModel") FaqsModel data,
			Model model,
			@RequestParam("file") MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String type = file.getContentType();
		if(type.equalsIgnoreCase("image/png") || type.equalsIgnoreCase("image/jpeg")){
			String uploadDir = "prevention-image/";
			
			FileUtility.saveFile(file, uploadDir, fileName);
			data.setImage(fileName);
			this.faqsRepo.save(data);
		}else {
			System.out.println("Bukan format yang benar");
		}
		
		
		return "redirect:/faqs/input";
	}
	
	
	
}
































//@GetMapping("/")
//public String helloWorld(){
//	return "covid/src/html/index";
//}

