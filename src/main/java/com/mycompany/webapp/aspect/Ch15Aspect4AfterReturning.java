package com.mycompany.webapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Ch15Aspect4AfterReturning {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch15Aspect4AfterReturning.class);

	//Pointcut의 메서드가 정상적으로 끝났을때 실행
	//value라고 써도되고 의미있는 pointcut이라는 이름으로 써도 된다.
	@AfterReturning(
			pointcut="execution(public * com.mycompany.webapp.controller.Ch15Controller.afterReturning*(..))",
			returning="returnValue"
	)
	public void method(String returnValue) {
		logger.info("실행");
		logger.info("리턴값 "+ returnValue);
	}
}
