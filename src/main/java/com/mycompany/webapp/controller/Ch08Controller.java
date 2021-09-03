package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

@Controller
@RequestMapping("/ch08")
@SessionAttributes({"inputForm"})
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
	
	@GetMapping("/login")
	public String loginForm() {
		logger.info("실행");
		return "ch08/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		if(mid.equals("spring")&&mpassword.equals("12345")) {
			session.setAttribute("sessionMid", mid);
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("실행");
		
		//방법1 . 직접 mid를 찾아서 지우기
		session.removeAttribute("sessionMid");
		
		//방법2 . session 객체 지우기(session안에 모든 데이터가 날라간다.)
		//session.invalidate();
		
		return "redirect:/ch08/content";
	}
	
	
	@PostMapping(value="/loginAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		logger.info("실행");
		String result = "";
		
		if(!mid.equals("spring")) {
			result = "wrongMid";
		} else if(!mpassword.equals("12345")) {
			result = "wrongMpassword";
		} else {
			result = "success";
			session.setAttribute("sessionMid", mid);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		String json = jsonObject.toString();
		return json;
	}
	
	@GetMapping(value="/logoutAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(HttpSession session) {
		logger.info("실행");

		session.invalidate();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		return json;
	}
	
	//모든 요청에 대해 아래부분은 실행된다.
	//그런데 위에 Class 부분에 @SessionAttributes가 붙으면 한번만 실행된다. 위를 참고하자.
	//요청할때마다 실행되면 inputForm에 들어간 데이터가 매번 바뀐다.
	//그래서 위에서 sessionAttribute를 넣어주면
	//딱 한번만 실행한다.
	//딱 한번이 언제 실행할까?
	//"inputForm"이라는 이름이 없을때만 딱 한번 실행한다.
	
	//세션에 "inputForm" 이름이 존재하지 ㅇ낳을 경우 딱 한번 실행.
	@ModelAttribute("inputForm")
	public Ch08InputForm getInputFrom() {
		Ch08InputForm inputFrom = new Ch08InputForm();
		return inputFrom; 
	}
	
	
	
	//이곳에서 공통적으로 쓸것이다.
	@GetMapping("/inputStep1")
	public String inputStep1(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		return "ch08/inputStep1";
	}
	
	//이곳에서 공통적으로 쓸것이다.
	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		logger.info("data1 : "+ inputForm.getData1());
		logger.info("data2 : "+ inputForm.getData2());
		logger.info("data3 : "+ inputForm.getData3());
		logger.info("data4 : "+ inputForm.getData4());
		return "ch08/inputStep2";
	}
	
	@PostMapping("/inputDone")
	public String inputDone(@ModelAttribute("inputForm") Ch08InputForm inputForm, SessionStatus sessionStatus) {
		logger.info("실행");
		logger.info("data1 : "+ inputForm.getData1());
		logger.info("data2 : "+ inputForm.getData2());
		logger.info("data3 : "+ inputForm.getData3());
		logger.info("data4 : "+ inputForm.getData4());
		//처리 내용~
		//세션에 저장되어 있는 inputForm을 제거
		//"inputform"객체가 날라간다.
		sessionStatus.setComplete();
		
		//아까 배운 아래 방식으로는 지울 수 없다.
		//session.removeAttribute("inputform");
		return "redirect:/ch08/content";
	}
	
}
