package com.sse.app.ioc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.sse.app.robot.Robot;

@SpringBootTest
class IocTest {

	@Autowired
	private Robot robot;
	
	@Test
	void test() {
		System.out.println("Test Case");
		robot.getRobotArm().punch();
		assertNotNull(robot);
	}

}
