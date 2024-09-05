package com.sse.app.aops.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StartTest {
	
	@Autowired
	private Start start;

	@Test
	void goTest() {
		start.go();
	}

}
