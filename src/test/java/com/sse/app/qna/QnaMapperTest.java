package com.sse.app.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.sse.app.util.Pager;

@SpringBootTest
@Transactional //모든 테스트 메서드 실행 후 전부 롤백 처리 하려고 주는 어노테이션
class QnaMapperTest {
	
	@Autowired
	private QnaMapper qnaMapper;

//	@Test
//	void getDetailTest() throws Exception{
//		QnaVO qnaVO = new QnaVO();
//		qnaVO.getBoardNum();
//		qnaVO = qnaMapper.getDetail(qnaVO);
//	}
	
	@Test
	void addTest() throws Exception {
		
		for(int i = 3; i < 110; i++) {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardContents("c" + i);
		qnaVO.setBoardTitle("c3" + i);
		qnaVO.setBoardWriter("w3" + i);
		qnaVO.setRef((long)i);
		qnaVO.setStep(0L);
		qnaVO.setDepth(0L);
		int result = qnaMapper.add(qnaVO);
		if(i % 10 == 0) {
			Thread.sleep(500);
		}
		}
		
	}
	
//	@Test
	@Rollback(false) //메서드 실행 후 롤백 안 하겠다(클래스에 @Transactional 줬지만 예외를 주는 것)
	void getListTest() throws Exception{
		Pager pager = new Pager();
		
		pager.setPage(1L);
		pager.setKind("k1");
		pager.setSearch("2");
		pager.makeRow();
		
		List<QnaVO> ar = qnaMapper.getList(pager);
		assertNotEquals(0, ar.size());
		
	}

}
