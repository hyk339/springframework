package com.mycompany.webapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


//이렇게 Component를 쓸 경우 dispatcher에서만 쓸 수 있다.
//그래서 ch17_security.xml에 bean으로 만들어주자.

public class Ch17AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(Ch17AuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("실행");
		super.onAuthenticationFailure(request, response, exception);
	}
}
