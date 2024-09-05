package com.sse.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sse.app.util.FileManager;
import com.sse.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.qna}")
	private String name;
	
	@Autowired
	private FileManager fileManager;
	
	public List<QnaVO> getList(Pager pager) throws Exception{
		pager.makeRow();
		log.info("Upload path:{}",upload);
		
		return qnaMapper.getList(pager);
	}
	
	public int add(QnaVO qnaVO,MultipartFile[] attaches) throws Exception {

		int result = qnaMapper.add(qnaVO);
	
		result = qnaMapper.refUpdate(qnaVO);
		
//		예외를 발생시키겠다 그럼 if만 실행하고 이 함수는 예외를 던지고 끝나게 됨
		if(result==1) {
			throw new Exception();
		}
		
		//파일을 HDD(하드디스크)에 저장 후 DB에 정보를 추가
		for(MultipartFile mf : attaches) {
			if(mf==null||mf.isEmpty()) {
				continue;
			}
//			D:/upload/qna 경로가 완성!
			String fileName = fileManager.fileSave(upload+name,mf);
			
			QnaFileVO qnaFileVO = new QnaFileVO();
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(mf.getOriginalFilename());
			qnaFileVO.setBoardNum(qnaVO.getBoardNum());
			
			result = qnaMapper.addFile(qnaFileVO);
			
		}
		
		return result;
		
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception{
		return qnaMapper.getFileDetail(qnaFileVO);
	}
	
}