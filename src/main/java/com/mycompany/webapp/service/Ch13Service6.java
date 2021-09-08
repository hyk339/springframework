package com.mycompany.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//기본생성자가 없으면 
//나머지중에서 주입을 통해서 값을 주고 호출할수 있는 생성자가 있는지 찾는다.
//있다면 그것을 가지고 객체를 생성시킨다.

@Service
public class Ch13Service6 {
	private static final Logger logger = LoggerFactory.getLogger(Ch13Service6.class);
	
	private int prop1;
	private double prop2;
	private boolean prop3;
	
	@Value("${service.prop4}") 
	private String prop4;
	
	public Ch13Service6(
			@Value("${service.prop1}") int prop1, 
			@Value("${service.prop2}") double prop2) {
		logger.info("실행");
		logger.info("prop1: "+ prop1);
		logger.info("prop2: "+ prop2);
		this.prop1 = prop1;
		this.prop2 = prop2;
	}
	

	
	//아래는 setProp4를 메서드 호출을 안했는데 실행된다.
	@Value("${service.prop3}")
	public void setProp3(boolean prop3) {
		logger.info("실행");
		logger.info("prop3: "+ prop3);
		this.prop3 = prop3;
	}
	
}
