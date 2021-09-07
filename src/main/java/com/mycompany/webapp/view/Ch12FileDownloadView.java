package com.mycompany.webapp.view;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


@Component
public class Ch12FileDownloadView extends AbstractView{
	
	private static final Logger logger = LoggerFactory.getLogger(Ch12FileDownloadView.class);
	
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		logger.info("실행");

		String fileName = (String) model.get("fileName");
		String userAgent = (String) model.get("userAgent");
		
		//fileNo를 이용해서 DB에서 파일 정보 가져오기
		
		//자동으로 mime 파일 확장명을 가지고 얻어내는 방법이 있다.
		String contentType = request.getServletContext().getMimeType(fileName);
		String originFilename = fileName;
		String savedName = fileName;
		
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
