package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class Ch15Aspect1Before {
	private static final Logger logger = LoggerFactory.getLogger(Ch15Aspect1Before.class);
	
	//Ch15Controller.*(..)는 Ch15Controller의 모든 메서드에 적용하겠다. (..)은 매개변수는 있던 없던 상관없다.
	//즉, Ch15Controller 밑에 public인 모든 method가 대상이 된다.
	//아래는 controller 패키지 안에 모든 Controller가 대상이 된다.
	//"execution(public * com.mycompany.webapp.controller.*.*(..)"
	//아래는 before로 시작하는 메서드를 실행할때 적용하겠다는 것을 말한다.
	//"execution(public * com.mycompany.webapp.controller.Ch15Controller.before*(..)"
	@Before("execution(public * com.mycompany.webapp.controller.Ch15Controller.before*(..))")
	public void method() {
		logger.info("실행");
	}
}
	