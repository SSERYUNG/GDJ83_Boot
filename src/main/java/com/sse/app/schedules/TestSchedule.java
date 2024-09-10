package com.sse.app.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sse.app.qna.QnaMapper;
import com.sse.app.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	@Autowired
	private QnaMapper qnaMapper;
	
//	(밑에 걸로 설명하면 서버 실행하고 2초 있다가(initialDelay), 1초마다 로그를 찍어라 이런 뜻임(fixedDelay))
//	@Scheduled(fixedDelay = 1000, initialDelay = 2000)
	public void test1() throws Exception {
		
		log.error("Schedule Test");
		
	}
	
//	@Scheduled(fixedRate = 2000)
	public void test2() throws Exception {
		
		log.error("============ Schedule Test ============");
		
	}
	
//	@Scheduled(cron = "*/5 * * * * *")
	public void test3() throws Exception {
		
		log.error("============ Schedule Test ============");
		
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardWriter("Test");
		qnaVO.setBoardTitle("Title");
		qnaVO.setBoardContents("Contents");
		qnaMapper.add(qnaVO);
		qnaMapper.refUpdate(qnaVO);
		
		
	}

}
