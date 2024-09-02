package com.sse.app.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RobotConfig {
	
//	<bean class=> 레거시에서 썼던 이것과 같은 역할
	@Bean("ra")
	RobotArm makeArm() {
		return new RobotArm();
	}

	@Bean
	Robot makeRobot() {
		Robot robot = new Robot();
		robot.setRobotArm(makeArm());
		return robot;
	}
	
}
