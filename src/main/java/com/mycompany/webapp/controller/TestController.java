package com.mycompany.webapp.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/main")
	public String main() {
		return "test/main";
	}
	
	/*@RequestMapping("/makeid")
	public String makeId(String name, String id, String pass, String age) {
		logger.info("name : "+name);
		logger.info("id : "+id);
		logger.info("pass : "+pass);
		logger.info("age : "+age);
		return "redirect:/test/main";
	}*/
	
	@RequestMapping(value="/makeid", method=RequestMethod.POST)
	public String makeId(
			@RequestParam(defaultValue = "없음")
			String name, 
			@RequestParam(defaultValue = "없음")
			String id, 
			@RequestParam(defaultValue = "1234")
			int pass, 
			@RequestParam(defaultValue = "0")
			int age,
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date date,
			@RequestParam(defaultValue = "false")
			boolean radio1
			) {
		logger.info("name(get) : "+name);
		logger.info("id(get) : "+id);
		logger.info("pass(get) : "+pass);
		logger.info("age(get) : "+age);
		logger.info("date(get) : "+date);
		logger.info("radio1(get) : "+radio1);
		return "redirect:/test/main";
	}
	
	@RequestMapping(value="/putmethod", method=RequestMethod.PUT)
	public String putMethod() {
		logger.info("실행");
		return "redirect:/test/main";
	}
	
	@RequestMapping("/deletemethod")
	public String deleteMethod() {
		logger.info("실행");
		return "redirect:/test/main";
	}
}
