package com.mycompany.webapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


//이렇게 Component를 쓸 경우 dispatcher에서만 쓸 수 있다.
//그래서 ch17_security.xml에 bean으로 만들어주자.


//extends SimpleUrlAuthenticationSuccessHandler는 로그인 하면 무조건 defaulttargeturl로 보낸다. 단순하다.
//그래서 다른 것을 상속해야한다.
//extends SavedRequestAwareAuthenticationSuccessHandler를 사용해야한다.해석해보면 기억해서 핸들러를 처리하겠다는 내용이다.
//보통 simple을 많이 사용한다.


/*
SimpleUrlAuthenticationSuccessHandler
   로그인 성공후에 무조건 홈으로 이동
SavedRequestAwareAuthenticationSuccessHandler
   로그인 성공후에 사용자가 접근하고자 했던 페이지로 이동
*/
public class Ch17AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(Ch17AuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("실행");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
