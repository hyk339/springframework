package com.mycompany.webapp.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Ch03Dto;

@Controller
@RequestMapping("/ch03") //여기는 contextroot가 생략되어있어서 안넣어도 된다.
public class Ch03Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch03Controller.class);
	//slf4j를 항상 import하자
	
	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch03/content";
	}
	
	/*@GetMapping("/method1")
	public String method1( 
			@RequestParam() p1,
			String p2, 
			String p3,
			String p4,
			String p5
	) {
	
		logger.info("실행");
		logger.info("param1:"+param1);
		logger.info("param2:"+param2);
		logger.info("param3:"+param3);
		logger.info("param4:"+param4);
		logger.info("param5:"+param5);
		
		return "redirect:/ch03/content";
	}*/
	
	/*@GetMapping("/method1")
	public String method1( //이렇게 매개변수로 받으면 jsp에서 보낸 파라미터들이 받아진다. 이름과 똑같은 매개변수 명을 사용해야한다.
			String param1,
			String param2, //null은 숫자로 바꿀수 없다.
			String param3,
			String param4,
			String param5
	) {
	
		logger.info("실행");
		logger.info("param1:"+param1);
		logger.info("param2:"+param2);
		logger.info("param3:"+param3);
		logger.info("param4:"+param4);
		logger.info("param5:"+param5);
		
		return "redirect:/ch03/content";
	}*/
	
	
	@GetMapping("/method1")
	//기본적으로 전부 String으로 받아오는데 Spring이 알아서 int, double, boolean으로 대입해준다.
	//객체 하나로 받아올 수 도 있다.
	public String method1( 
			String param1,
			@RequestParam(defaultValue = "0") int param2, 
			//@RequestParam(value ="param2" defaultValue = "0") int p2, 
			//param2가 null인 상태로 넘어오게되면 에러가난다.
			//그래서 위처럼 default를 넣어주면 에러가 나지 않는다.
			@RequestParam(defaultValue = "0.0") double param3,
			@RequestParam(defaultValue = "false")boolean param4,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date param5
	) {
	
		logger.info("실행");
		logger.info("param1:"+param1);
		logger.info("param2:"+param2);
		logger.info("param3:"+param3);
		logger.info("param4:"+param4);
		logger.info("param5:"+param5);
		
		return "redirect:/ch03/content";
	}
	
	/*
	@GetMapping("/method1")
	//기본적으로 전부 String으로 받아오는데 Spring이 알아서 int, double, boolean으로 대입해준다.
	//객체 하나로 받아올 수 도 있다.
	public String method1(Ch03Dto dto) {
	
		logger.info("실행");
		logger.info("param1:"+dto.getParam1());
		logger.info("param2:"+dto.getParam2());
		logger.info("param3:"+dto.getParam3());
		logger.info("param4:"+dto.isParam4()); //boolean type의 getter는 is로 시작한다.
		logger.info("param5:"+dto.getParam5());
		
		return "redirect:/ch03/content";
	}
	*/
	
	/*@PostMapping("/method2")
	public String method2(
			String param1,
			String param2,
			String param3,
			String param4,
			String param5
	) {
		logger.info("실행");
		logger.info("param1:"+param1);
		logger.info("param2:"+param2);
		logger.info("param3:"+param3);
		logger.info("param4:"+param4);
		logger.info("param5:"+param5);
		return "redirect:/ch03/content";
	}*/
	
	/*@PostMapping("/method2")
	public String method2(
			String param1,
			@RequestParam(defaultValue = "0") int param2, 
			@RequestParam(defaultValue = "0.0") double param3,
			@RequestParam(defaultValue = "false")boolean param4,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date param5
	) {
		logger.info("실행");
		logger.info("param1:"+param1);
		logger.info("param2:"+param2);
		logger.info("param3:"+param3);
		logger.info("param4:"+param4);
		logger.info("param5:"+param5);
		return "redirect:/ch03/content";
	}*/
	
	@PostMapping("/method2")
	public String method2(Ch03Dto dto) {
		logger.info("실행");
		logger.info("param1:"+dto.getParam1());
		logger.info("param2:"+dto.getParam2());
		logger.info("param3:"+dto.getParam3());
		logger.info("param4:"+dto.isParam4());
		logger.info("param5:"+dto.getParam5());
		return "redirect:/ch03/content";
	}
	
}
