package com.mycompany.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ch01")
public class Ch01Controller {
	//상수에 이름이 Logger라고 되어있고 LoggerFactory객체의 static 메서드인 getLogger를 쓴다.
	//getLogger의 매개변수로 Ch01Controller.class를 넣었다.
	private static final Logger logger = LoggerFactory.getLogger(Ch01Controller.class);
	
	@RequestMapping("/content") //클라이언트가 이와같이 요청할 경우에 아래를 실행한다. //ch01 앞에는 context root까지 생략되어있다.
	public String home() {
		//logger.debug("실행1");
		logger.info("실행2");
		//logger.warn("실행3");
		//logger.error("실행4");
		return "ch01/content";
		// views 안에 ch01 폴더를 만들고 실행해보면 실행이 return "home";일때는 안된다.
		// 이때 return "ch01/home"; 이라고 주면 된다.
	}
} 
