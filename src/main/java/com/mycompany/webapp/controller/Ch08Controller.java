package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/ch08")
public class Ch08Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch08Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}
	
	@GetMapping(value="/saveData", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String saveData(String name, HttpServletRequest request, HttpSession session) {
		logger.info("실행");
		logger.info("name:"+name);
		
		//HttpSession session = request.getSession();
		session.setAttribute("name", name);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString(); // {"result":"success"}
		return json;
	}

	@GetMapping(value="/readData", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String readData(HttpSession session, @SessionAttribute String name) {
		logger.info("실행");
		//session이 get할때 어떤 타입을 가져올지 모르니까 object로 가져온다.
		
		//방법1
		//String name = (String) session.getAttribute("name");
		
		logger.info("name : "+ name);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString(); // {"name":"홍길동"}
		return json;
	}
}
