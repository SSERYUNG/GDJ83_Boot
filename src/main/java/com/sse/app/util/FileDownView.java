package com.sse.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.sse.app.qna.QnaFileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//BeanNameViewResolve
public class FileDownView extends AbstractView{
	
	@Value("${app.upload}")
	private String path;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		QnaFileVO qnaFileVO = (QnaFileVO) model.get("file");
		String directory = (String) model.get("board");

//		1.폴더 경로 준비
//		//D:/upload.qna 경로 완성
		String path = this.path+directory;

//		2. 파일 준비
		File file = new File(path, qnaFileVO.getFileName());

//		3. 응답시 인코딩 처리(Filter로 처리 했으면 선택)
		response.setCharacterEncoding("UTF-8");

//		4. 파일의 크기 지정
		response.setContentLength((int) file.length());

//		5.다운로드 시 파일 이름을 지정,이름이 한글일수 있어서 이것도 인코딩
		String name = qnaFileVO.getOriName();
		name = URLEncoder.encode(name, "UTF-8");

//		6. Header 정보 설정 (데이터 정보를 담고 있는 것)
		response.setHeader("Content-Disposition", "attachment;fileName=\"" + name + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");

//		7. 클라이언트에게 전송(0과 1로 만들어서)
//		HDD에서 파일을 읽어와서 Client로 output

		FileInputStream fi = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
//		파일에서 읽어온거를 카피 해서 아웃풋으로 내보내라
		FileCopyUtils.copy(fi, os);

//		8. 자원을 썼으면 해제해주기
		os.close();
		fi.close();

	}


}
