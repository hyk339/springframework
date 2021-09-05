package com.mycompany.webapp.controller;

import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
@RequestMapping("/ch05")
public class Ch05Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch05Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}
	
	@GetMapping("/getHeaderValue")
	public String getHeaderValue(HttpServletRequest request) {
		logger.info("실행");
	
		logger.info("method: "+request.getMethod());
		//getRequestURI()는 contextroot이후의 경로이다.
		logger.info("requestURI: "+ request.getRequestURI());
		//요청한 브라우저의 ip
		logger.info("client IP: "+ request.getRemoteAddr());
		logger.info("contextRoot: "+request.getContextPath());
		//client가 사용하는 브라우져, os의 종류도 알수있다.
		//요청헤더에 보면 
		String userAgent = request.getHeader("User-Agent");
		logger.info("User-Agent: "+userAgent);
		
		if(userAgent.contains("Windows NT")) {
			logger.info("client OS: Windows");
		}else if(userAgent.contains("Macintosh")) {
			logger.info("client OS: MacOS");
		}
	
		if(userAgent.contains("Edg")) {
			logger.info("client Browser: Edge");
		} else if(userAgent.contains("Trident")) {
			logger.info("client Browser: IE11");
		} else if(userAgent.contains("Chrome")) {
			logger.info("client Browser: Chrome");
		} else if(userAgent.contains("Safari")) {
			logger.info("client Browser: Safari");
		}
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("createCookie")
	public String createCookie(HttpServletResponse response) { //response를 응답을 만들때 쓴다.
		logger.info("실행");
		
		Cookie cookie = new Cookie("useremail","blueskii@naver.com"); //반드시 문자열만 저장해야한다. 문자열로 만든 json도 넣을수있다.
		
		
		//다른 도메인을 지정하는 경우는 거의 없다.
		//다른 도메인으로 갈때 쿠키를 들고간다는 것이다.
		cookie.setDomain("localhost");//localhost이면 전송 //domain부분도 바꿀수 있다.
		//cookie.setPath("/ch05"); //webapp이라는 context root를 사용하면 그부분도 넣어주어야한다.
		cookie.setPath("/"); //localhost/...이면 모두 전송
		cookie.setMaxAge(30*60); // 이 시간동안에만 전송//30분*60초 //서버를 닫아도 살아있다.
		cookie.setHttpOnly(true); //Javascript에서 못읽게 함.
		cookie.setSecure(true); //https://만 전송
		//브라우저에게 번호표를 주는 행위
		response.addCookie(cookie);
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("getCookie1")
	//public String getCookie(@CookieValue String useremail) {
	public String getCookie1(
			@CookieValue String useremail,
			@CookieValue("useremail") String uemail) { //쿠키의 이름과 사용하려는 이름이 다른경우
		logger.info("실행");
		
		logger.info("useremail :"+useremail);
		logger.info("uemail :"+uemail);
		//쿠키를 얻는 2가지 방법이있다.
		
		return "redirect:/ch05/content";
	}
	
	@GetMapping("getCookie2")
	public String getCookie2(HttpServletRequest request) {
		logger.info("실행");
		//잘 사용하지는 않지만 알고는 있자.
		//request를 통해서도 얻을수 있다는 것을 보여주었다.
		 Cookie[] cookies = request.getCookies(); //쿠키가 여러개 일수도 있으니까 배열로 받는다.
		 for(Cookie cookie : cookies) {
			 String cookieName = cookie.getName();
			 String cookieValue = cookie.getValue();
			 if(cookieName.equals("useremail")) {
				 logger.info("useremail:"+cookieValue);
				 break;
			 }
			 
		 }
		return "redirect:/ch05/content";
	}
	
	@GetMapping("createJsonCookie")
	public String createJsonCookie(HttpServletResponse response) throws Exception{
		logger.info("실행");
		//Json으로 만드는게 매우 껄끄럽다..
		//그러다보니 json을 만들어주는 library를 써주어야한다.
		
		//현업에서는 암호화시키는게 일반적이다.
		// ' \" '가 json에서 안먹힌다. 위에 String이 안된다.. 

		//String json = "{\"userid\":\"fall\", \"useremail\":\"fall@company.com\", \"username\":\"홍길동\"}";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userid", "fall");
		jsonObject.put("useremail", "fall@company.com");
		jsonObject.put("username", "홍길동");
		String json = jsonObject.toString();
		//한글이 있다면 try catch는 필수다.
		logger.info(json);
		json = URLEncoder.encode(json, "UTF-8"); //인코드해서 json을 아래처럼 만든다.
		//아스키 문자로 다 치환시킨다.
		logger.info(json);
		Cookie cookie = new Cookie("user", json); 
		//Cookie cookie = new Cookie("user", URLEncoder.encode("홍길동","UTF-8")); 
		//예전 버전은 한글이 안들어갔는데 들어간당. 
		//예전에는 무조건 urlencoder utf8을 해주어야했다.
		//쿠키는 헤더에 실려들어간다. 그래서 한글이 들어가면 안된다.
		
		response.addCookie(cookie);
		return "redirect:/ch05/content";
	}
	
	
	@GetMapping("/getJsonCookie")
	public String getJsonCookie(@CookieValue String user) {
		logger.info("실행");
		logger.info("user:" + user);
		//문자열을 json으로 만들지만 json을 문자열로 만들기도 한다.
		JSONObject jsonObject = new JSONObject(user);
		String userid = jsonObject.getString("userid");
		String useremail = jsonObject.getString("useremail");
		String username = jsonObject.getString("username");
		logger.info("userid :"+userid);
		logger.info("useremail :"+useremail);
		logger.info("username :"+username);
		
		//그런데 현업에서는 잘 안쓴다.
		//userid나 email이 노출될수있다.
		//진짜 보이면 안되는 데이터는 암호화를 해야한다.
		//암호화하는게 표준으로 나와있는게 있어서 그것을 사용하면된다
		//JSON Wen Token이라고 있다. JWT이다.
		return "redirect:/ch05/content";
	}
	
	//쿠키에 암호화해서 보내는 방법 중 하나.
	//표준이기에 사용하자.
	@GetMapping("/createJwtCookie")
	public String createJwtCookie(HttpServletResponse response) throws Exception {
		logger.info("실행");
		
		String userid = "fall";
		String useremail = "fall@mycompany.com";
		String username = "홍길동";
		
		JwtBuilder builder = Jwts.builder(); 
		//-------Header-------
		//어떤 알고리즘을 쓰는지, 타입이 뭔지 설정하자, jwtio사이트에 나와있는(빨간색 부분 : 알고리즘,타입)
		builder.setHeaderParam("alg", "HS256");
		builder.setHeaderParam("typ", "JWT"); 
		//헤더 설정 끝
		//JWT도 유효시간이 있다. 
		//지금으로부터 30분 혹은 하룻동안 혹은 한달동안 이렇게 유효기간을 설정해야한다.
		builder.setExpiration(new Date(new Date().getTime()+1000*60*30)); //1000이 1초
		//시간을 long값으로 얻는다는 뜻? 
		//1970년 자정을 기준으로 1000분의 1초로 카운팅한 수.

		//-------Payload-------
		//이번에는 payload, claim부분을 설정하자(보라색 부분 : 내용)
		builder.claim("userid", userid); //여러개일경우 addclaims를 사용할수 있다. 
		builder.claim("useremail", useremail);
		builder.claim("username", username);
		
		//-------Signature-------
		String secretKey = "abc12345";
		//이런 key는 property에 넣어두고 불러와서 사용한다.
		builder.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"));
		String jwt = builder.compact();
		logger.info("jwt :"+jwt);
		
		Cookie cookie = new Cookie("jwt", jwt);
		response.addCookie(cookie);
		return "redirect:/ch05/content";
		
	}
	
	@GetMapping("/getJwtCookie")
	public String getJwtCookie(@CookieValue String jwt) throws Exception {
		logger.info("실행");
		logger.info(jwt);
		JwtParser parser = Jwts.parser();
		String secretKey = "abc12345";
		parser.setSigningKey(secretKey.getBytes("UTF-8"));
		//payload에 있는 하나하나 속성과 값
		Jws<Claims> jws = parser.parseClaimsJws(jwt);
		Claims claims = jws.getBody();
		String userid = claims.get("userid",String.class);
		String useremail = claims.get("useremail",String.class);
		String username = claims.get("username",String.class);
		logger.info("userid :"+userid);
		logger.info("useremail :"+useremail);
		logger.info("username :"+username);
		
		return "redirect:/ch05/content";
	}
}
