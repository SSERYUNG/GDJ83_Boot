package com.sse.app.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeMapperTest {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Test
	void getListTest() throws Exception {
		
//		List<NoticeVO> ar = noticeMapper.getList();
//		assertNotEquals(0, ar.size());
//		
//		for(NoticeVO n : ar) {
//			System.out.println(n.toString());
//		}
		
		int result = noticeMapper.addNotice();
		assertEquals(1, result);
		
	}
}
