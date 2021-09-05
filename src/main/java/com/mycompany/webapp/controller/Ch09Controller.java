package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("/fileupload")
	public String fileupload(String title, String desc, MultipartFile attach) throws Exception {
		logger.info("실행");
		
		//문자 파트 내용 읽기
		logger.info("title:"+title);
		logger.info("desc:"+desc);
		
		//파일 파트 내용 읽기
		logger.info("file originalname: "+ attach.getOriginalFilename());
		logger.info("file contenttype: "+attach.getContentType());
		logger.info("file size: "+attach.getSize());
		
		//파일 파트 데이터를 서버의 파일로 저장
		//가능하면 저장이름과 orginal name은 다르게 설정하는게 좋다.
		//아래처럼 시간을 등록하면 절대 중복될수가 없다.
		String savedname = new Date().getTime() +"-"+ attach.getOriginalFilename();
		File file = new File("C:/hyundai_it&e/upload_files/"+savedname);
		attach.transferTo(file);
		
		return "redirect:/ch09/content";
	}
	
	@PostMapping(value="/fileuploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(String title, String desc, MultipartFile attach) throws Exception {
		logger.info("실행");
		
		//문자 파트 내용 읽기
		logger.info("title:"+title);
		logger.info("desc:"+desc);
		
		//파일 파트 내용 읽기
		logger.info("file originalname: "+ attach.getOriginalFilename());
		logger.info("file contenttype: "+attach.getContentType());
		logger.info("file size: "+attach.getSize());
		
		//파일 파트 데이터를 서버의 파일로 저장
		//가능하면 저장이름과 orginal name은 다르게 설정하는게 좋다.
		//아래처럼 시간을 등록하면 절대 중복될수가 없다.
		String savedname = new Date().getTime() +"-"+ attach.getOriginalFilename();
		File file = new File("C:/hyundai_it&e/upload_files/"+savedname);
		attach.transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("savedname", savedname);
		String json = jsonObject.toString();
		return json;
	}
	
	
	//아래도 문제가 있는게 image 파일이라고 보장은 못한다.
	//png,jpg등 다양하게 존재한다.
	//그래서 produces가 고정이 되면 안된다.
	//나중에 db에 orginalfilename, contenttype등 다 넣어야한다.
	//응답 바디의 데이터 형식 고정
	//tobyteArray()에 리턴하는 배열의 길이 문제
	/*
	@GetMapping(value="/filedownload", produces = "image/jpeg")
	@ResponseBody
	public byte[] filedownload(String savedname) throws Exception { //원래 매개값으로 int타입 filenum을 받아올것이다.
		//우리가 db에서 filename을 읽어온다고 가정해보자
		String filePath = "C:/hyundai_it&e/upload_files/"+savedname;

		//filePath에 있는 파일을 읽는다.
		InputStream is = new FileInputStream(filePath);

		//byte배열로 만든다.
		byte[] data = IOUtils.toByteArray(is);

		//현재 이 방법은 좋은 방법이 아니다.
		return data;
	}
	*/
	
	
	@GetMapping(value="/filedownload")
	public void filedownload(
			int fileNo, 
			HttpServletResponse response,
			@RequestHeader("User-Agent") String userAgent) throws Exception {
		//fileNo를 이용해서 DB에서 파일 정보 가져오기
		
		//브라우져는 읽을때 contentType = "image/jpeg"이면 본인이 가장 잘 연다고 생각하고 파일을 바로 열어버린다.
		//그래서 브라우져가 바로 열지 않도록 파일을 강제다운로드 하도록 설정해보자.
		String contentType = "image/jpeg";
		String originFilename = "눈내리는마을.jpg";
		String savedName = "1630656717326-눈내리는마을.jpg";
		
		//응답 body의 데이터의 형식 설정
		response.setContentType(contentType);
		
		//브라우저별 한글 파일명 변환
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			//IE일 경우 msie(11이하버전)
			originFilename = URLEncoder.encode(originFilename, "UTF-8");
		} else {
			//크롬,엣지스,사파리일경우
			//크롬 브라우저에서 한글 파일명 변환 
			//이거 안쓰면 한글파일다운로드할때 ____.jpg로 나온다.
			originFilename = new String(originFilename.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		//파일 첨부로 다운로드하도록 설정 //원래 올린 파일 명으로 그대로 다운로드 되게끔 해야한다. //header에 대한 값으로 아래철머 설정해주면 된다.
		response.setHeader("Content-Disposition", "attachment; filename=\""+originFilename+"\"");
		
		//파일로부터 데이터를 읽는 입력스트림 생성
		String filePath = "C:/hyundai_it&e/upload_files/"+savedName;
		InputStream is = new FileInputStream(filePath);

		//응답 바디에 출력하는 출력스트림 얻기
		OutputStream os = response.getOutputStream();
		
		//입력스트림 -> 출력스트림 //조금조금씩 읽고 출력하기때문에 메모리를 많이 차지하지 않는다.
		FileCopyUtils.copy(is, os);
		is.close();
		os.flush();
		os.close();
		
		//이게 메모리를 적게 차지하기때문에 좋은 방법이다.
	}
	
	
	
	
}
