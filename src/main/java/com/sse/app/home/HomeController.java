package com.sse.app.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sse.app.aops.main.Start;

@Controller
public class HomeController {
	
	@Autowired
	private Start start;

	@GetMapping("/")
	public String home() throws Exception {
		
		start.go();
		
		return "index";
		
	}
	
}
