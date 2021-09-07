package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch12")
public class Ch12Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch12Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch12/content";
	}
}
