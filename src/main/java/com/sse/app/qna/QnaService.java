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
//		log.info("Insert before:{}", qnaVO.getBoardNum());
//		int result = qnaMapper.add(qnaVO);
//		log.info("Insert after:{}", qnaVO.getBoardNum());
//		result = qnaMapper.refUpdate(qnaVO);
		
		//파일을 HDD(하드디스크)에 저장 후 DB에 정보를 추가
		for(MultipartFile mf : attaches) {
			if(mf.isEmpty()||mf==null) {
				continue;
			}
			String fileName = fileManager.fileSave(upload+name,mf); //D:/upload/qna 경로가 완성!
			log.info("저장된 파일명: {}",fileName);
		}
		
		return 0;
//		return result;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
	
}