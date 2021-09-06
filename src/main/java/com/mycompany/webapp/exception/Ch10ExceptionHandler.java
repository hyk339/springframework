package com.mycompany.webapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mycompany.webapp.controller.Ch07Controller;


//Spring framework이 이것을 이용하도록 알려주어야한다. @Component을 사용한다.
//Ch10ExceptionHandler라는 클래스를 만들었으니 Spring 너가 객체를 생성해서 관리해줘.
//미리 객체를 생성시켜놔.
//@Component가 없으면 객체가 생성이 안된다.

//@ControllerAdvice는 조언자 역할을 하라고 하는 것인데 
//이거를 붙여서 Controller와 관계가 맺어진다.
//@Controller가 붙어있는 모든 controller와 관련이 있게 된다.

//어떤 controller에서건 nullpointerexception이 발생하면 아래 메서드가 실행된다.


//객체로 생성해서 관리하도록 설정
@Component
//모든 컨트롤러에 영향을 미치는 설정
@ControllerAdvice
public class Ch10ExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch10ExceptionHandler.class);
	
	public Ch10ExceptionHandler() {
		logger.info("실행");
	}
	
	//예외 처리자 설정
	@ExceptionHandler
	public String handleNullPointerException(NullPointerException e) { //nullpointerexception이 발생하면 이게 실행된다.
		//어떤 controller에서던 nullpointer exception이 발생하면 이부분이 실행된다.
		e.printStackTrace();
		logger.info("실행");
		return "error/500_null";
	}
	
	@ExceptionHandler
	public String handleClassCastException(ClassCastException e) { //nullpointerexception이 발생하면 이게 실행된다.
		logger.info("실행");
		e.printStackTrace();
		return "error/500_cast";
	}
	
	@ExceptionHandler
	public String handleClassCastException(Ch10SoldOutException e) { 
		logger.info("실행");
		e.printStackTrace();
		return "error/soldout";
	}
	
	
	//springframework이 알아서 구체적인 예외 먼저 찾고 일반적인 예외를 찾는다.그래서 순서는 상관없다.
	@ExceptionHandler
	//RuntimeException과 Exception의 차이
	//Exception을 상속한게 RuntimeException
	//runtimeException은 try catch를 하지 않아도 된다. compiler가 안붙여도 된다고 한다.
	//반면 다른 예외는 반드시 try catch하라고 compiler가 예외처리하라고 알려준다.
	//trycatch를 하는 exception은 이쪽으로 넘어오지 못한다. 왜냐하면 그 class에서 trycatch를 바로 처리해야하기때문
	public String handleRuntimeException(RuntimeException e) { 
		logger.info("실행");
		e.printStackTrace();
		return "error/500";
	}
}
