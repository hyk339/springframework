package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.dto.Ch07Board;
import com.mycompany.webapp.dto.Ch07City;
import com.mycompany.webapp.dto.Ch07Cloth;
import com.mycompany.webapp.dto.Ch07Member;

@Controller
@RequestMapping("/ch07")
public class Ch07Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch07Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch07/content";
	}
	
	@GetMapping("/saveData")
	public String savaData(HttpServletRequest request) {
		logger.info("실행");
		//HttpServletRequest request를 통해서 각 범위에 데이터를 저장할 수 있다.
		
		//Request범위에 데이터를 저장
		request.setAttribute("requestData", "자바");
		
		//Session 범위에 데이터를 저장
		HttpSession session = request.getSession();
		session.setAttribute("sessionData", "자바스크립트");
		
		//Application 범위에 데이터를 저장
		//servlet이 실행하는 환경의 객체
		//application 전체의 객체
		//브라우져가 달라도 전부 공유가 된다.
		ServletContext application = request.getServletContext();
		application.setAttribute("applicationData", "백앤드 스프링프레임워크");
		return "ch07/readData";
	}
	
	@GetMapping("/readData")
	public String readData() {
		logger.info("실행");
		return "ch07/readData";
	}
	
	@GetMapping("/objectSaveAndRead1")
	public String objectSaveAndRead1(HttpServletRequest request) {
		logger.info("실행");
		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);
		
		request.setAttribute("member", member);
		
		return "ch07/objectRead";
	}
	
	//아래방식은 아주 오래전 방식이다. 요즘에 잘 안쓴다. 현대에서 쓴다.ㅋㅋㅋㅋㅋ
	@GetMapping("/objectSaveAndRead2")
	public ModelAndView objectSaveAndRead2() { //데이터 뿐만아니라 View에 대한 정보도 같고 있다.
		logger.info("실행");
		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);
		
		ModelAndView mav = new ModelAndView(); //model은 데이터를 말한다. view는 뷰
		mav.addObject("member", member); //request범위에 저장한다. //request라고는 안써있지만 request에 저장
		mav.setViewName("ch07/objectRead");
		
		return mav;
	}
	
	//요즘은 아래처럼 대부분 작성한다. request에 여러개 저장하고 싶다면 
	//addAttribute를 여러개를 작성하면 된다.
	@GetMapping("/objectSaveAndRead3")
	public String objectSaveAndRead3(Model model) { //Model이라는 매개변수를 넣어준다. Model은 데이터를 말한다.
		logger.info("실행");
		
		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);
		
		model.addAttribute("member", member); //request범위에 저장한다. //request라고는 안써있지만 request에 저장
		
		return "ch07/objectRead";
	}
	
	@GetMapping("/useJstl1")
	public String useJstl1(Model model) {
		logger.info("실행");
		
		String[] languages = {"Java", "JavaScript", "SpringFramework", "Vue"};
		model.addAttribute("langs", languages);
		
		return "ch07/useJstl1";
	}
	
	@GetMapping("/useJstl2")
	public String useJstl2(Model model) {
		logger.info("실행");
		
		List<Ch07Board> list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			list.add(new Ch07Board(i,"제목"+i, "내용"+i, "글쓴이"+i, new Date()));
		}
		
		model.addAttribute("boardList", list);
		
		return "ch07/useJstl2";
	}
	
	//model.addAttribute("colors", colors); 이것과 같은 효과가 난다.
	//return 값이 값이 된다.
	//요청이 다른데 두군데서 다 사용할수 있다.
	//request 범위, session 벙위, application 범위일까?
	//request 범위에 저장한다.
	//why?
	//조건이 있다.
	//useModelAttribute1라고 요청이 오면 @ModelAttribute("colors")를 실행한다.
	//useModelAttribute2라고 요청이 오면 @ModelAttribute("colors")를 실행한다.
	//요청할때마다 @ModelAttribute("colors")를 실행한다.
	//그래서 이거는 어떤 요청이건 상관없이 항상 @ModelAttribute("colors")가 실행된다.
	//그리고 나서 jsp로 forward된다.
	
	
	//아래처럼 공통적인 데이터를 보내주는 경우는 많지는 않다. 그러나 그런 경우도 있다.
	//모든 jsp에서 공통적으로 사용하기를 원한다면 이렇게 써서 가져다 쓸수있다.
	@ModelAttribute("colors") 
	public String[] getColors() {
		logger.info("실행");
		String[] colors = {"Red", "Green", "Blue", "Yellow", "Pink"};
		return colors;
	}
	
	@GetMapping("useModelAttribute1")
	public String useModelAttribute1() {//Model model) {
		logger.info("실행");
		
		//String[] colors = {"Red", "Green", "Blue", "Yellow", "Pink"};
		//model.addAttribute("colors", colors);
		
		//위에 @ModelAttribute("colors")에 의해서 사용할수 있다.
		//공통 데이터가 된다.
		return "ch07/useModelAttribute";
	}
	
	@GetMapping("useModelAttribute2")
	public String useModelAttribute2() {//Model model) {
		logger.info("실행");
		
		//String[] colors = {"Red", "Green", "Blue", "Yellow", "Pink"};
		//model.addAttribute("colors", colors);
		
		return "ch07/useModelAttribute";
	}
	
	//이렇게 해도 되지만 더 간단한 방법이 있다. 받아온 매개변수 값을 다시 jsp로 보낼때,
	@GetMapping("/argumentSaveAndRead1")
	public String argumentSaveAndRead1(@ModelAttribute("kind") String kind, @ModelAttribute("sex") String sex){//, Model model) {
		//별도의 이름을 주어야 그 이름으로 값을 저장한다.
		logger.info("실행");
		
		logger.info("kind :"+kind);
		logger.info("sex :"+sex);
		
		//model.addAttribute("kind", kind);
		//model.addAttribute("sex", sex);
		
		return "ch07/argumentRead1";
	}
	
	@GetMapping("/argumentSaveAndRead2")
	//개별적으로 받는것은 이름으로 관리가 안된다.
	//객체를 매개변수로 받는것은 첫자를 소문자로 한 객체로 request에 자동적으로 저장된다.
	//command 객체는 기본적으로 request 범위에 저징이 되어서 첫글자를 소문자로한 ch07Cloth로 jsp에서 사용가능하다.
	public String argumentSaveAndRead2(@ModelAttribute("cloth") Ch07Cloth cloth) {
		logger.info("실행");
		
		logger.info("kind :"+cloth.getKind());
		logger.info("sex :"+cloth.getSex());
		
		return "ch07/argumentRead2";
	}
	
}
