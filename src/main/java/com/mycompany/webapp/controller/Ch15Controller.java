package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch15")
public class Ch15Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch15Controller.class);

	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch15/content";
	}
	
	@RequestMapping("before")
	public String before() {
		logger.info("실행");
		return "redirect:/ch15/content";
	}
	
}
