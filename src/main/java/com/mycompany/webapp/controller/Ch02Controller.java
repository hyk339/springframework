package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ch02")
public class Ch02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class);
	
	
	//view 폴더 안에 ch02 안에 content폴더
	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
		//info level의 log가 출력이 된다.
		return "ch02/content";
	}
	
	
	
	//어노테이션을 작성할때 값이 아나만 있으면 하나만 쓰면 되는데
	//2개이상이 들어가면 작성방법이 달라진다.
	//@Requestmapping의 경우 값이 하나면 value가 생략된것이다.
	
	//@GetMapping("/method")
	@RequestMapping(value="/method", method=RequestMethod.GET)
	public String method1() {
		logger.info("실행");
		return "redirect:/ch02/content";
	}	
	
	//@PostMapping("/method")
	@RequestMapping(value="/method", method=RequestMethod.POST)
	public String method2() {
		logger.info("실행");
		return "redirect:/ch02/content";
	}	
	
	//@PutMapping("/method")
	@RequestMapping(value="/method", method=RequestMethod.PUT)
	public String method3() {
		logger.info("실행");
		return "redirect:/ch02/content";
	}
	
	//@DeleteMapping("/method")
	@RequestMapping(value="/method", method=RequestMethod.DELETE)
	public String method4() {
		logger.info("실행");
		return "redirect:/ch02/content";
	}	
	
	
	
	//viewandmodel로 사용할때 객체로 만들어주고 setviewname을 하고 다시 리턴해야한다.
	//이렇게도 작성은 가능하다.
	
	@GetMapping("/modelandview") //경로는 보통 전부다 소문자로 하는것이 관례다!! camel X
	public ModelAndView method5() {
		logger.info("실행");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ch02/modelandview");
		return modelAndView;
	}
	
	@PostMapping("/login1")
	public String login1() {
		logger.info("실행");
		return "ch02/loginResult";
	}
	
	@PostMapping("/login2")
	public String login2() {
		logger.info("실행");
		return "redirect:/ch01/content";
	}
	
	@GetMapping("/boardlist")
	public String boardList() {
		return "ch02/boardList";
		//jsp는 대소문자 가린다.
		//jsp는 자바코드로 바뀌는데 자바는 대소문자를 가린다.
	}
	
	@GetMapping("/boardwriteform")
	public String boardWriteForm() {
		return "ch02/boardWriteForm";
	}
	
	@PostMapping("/boardwrite")
	public String boardWrite() {
		//url은 내용에 해당하는 내용이 나와야 정상이다.
		//return "ch02/boardlist"; 이렇게 하면 url은 write인데 내용은 list이고
		// 새로고침을 하면 계속 글이 입력이 된다.
		return "redirect:/ch02/boardlist";
	}
}
