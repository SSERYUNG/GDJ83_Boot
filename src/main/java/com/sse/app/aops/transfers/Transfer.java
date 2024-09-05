package com.sse.app.aops.transfers;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Transfer {
	
	public String takeBus(int num) {
		log.info("== 버스 타기 ==");
		
		return "BUS";
	}

	public void takeSubway(Long m, String name) {
		log.info("== 지하철 타기 ==");
	}
	
	public void walk() {
		log.info("== 걸어 가기 ==");
	}
	
}
