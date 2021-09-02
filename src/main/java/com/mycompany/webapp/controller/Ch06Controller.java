package com.mycompany.webapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch05Controller.class);
	
	@RequestMapping("/content") //클라이언트가 이와같이 요청할 경우에 아래를 실행한다. //ch01 앞에는 context root까지 생략되어있다.
	public String content() {
		return "ch06/content";
	}
	
	@RequestMapping("/forward") //클라이언트가 이와같이 요청할 경우에 아래를 실행한다. //ch01 앞에는 context root까지 생략되어있다.
	public String forward() {
		return "ch06/forward";
	}
	
	@RequestMapping("/redirect") //클라이언트가 이와같이 요청할 경우에 아래를 실행한다. //ch01 앞에는 context root까지 생략되어있다.
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/getFragmentHtml")
	public String getFragmentHtml() {
		logger.info("실행");
		return "ch06/fragmentHtml";
	}
	
	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws Exception {
		logger.info("실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo5.jpg");
		String json = jsonObject.toString();
		
		// 여기서부터 응답을 만듦. 그래서 void로 return 타입을 없앴다.
		// 응답 HTTP의 Body 부분에 json을 포함시키는 코드다. (아래부분)
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
		//위에서 바로 response로 응답해주기때문에 return이 필요가 없다.
	}
	
	//위를 아래처럼 쓸 수 있다.
	//위와 아래는 완전 같다.
	//위 방식은 직접 출력을 통해서 만들고 타입에 대한 설정도 해준것인 반면, 아래부분은 @responsebody방식은 아래처럼 쓰면된다.
	//아래 방식을 잘 알고있어야한다!!
	
	
	//이미지를 return 할대는 image/pack?이라고 쓰면 된다.
	@GetMapping(value="/getJson2", produces="application/json; charset=UTF-8")
	@ResponseBody //return 되는 값을 responsebody에 넣어라.
	public String getJson2() {
		logger.info("실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		return json;
	}
	
	@GetMapping("/getJson3")
	public String getJson3() {
		logger.info("실행");
		//개념적으로 이와같은 것은 맞지 않다.
		//된다고 해도 만들어서는 안된다.
		//ajax는 redirect로 응답을 받아서는 안된다.
		//이렇게 작성하는 경우는 절대절대 없다.
		return "redirect:/";
	}
}
