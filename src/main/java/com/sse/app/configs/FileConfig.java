package com.sse.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//설정 class 라는 것을 알려주기 위해 @을 준다(그러면 프로그램 시작할때 이걸 읽는다 like 레거시의 xml)
@Configuration

//WebMvcConfigurer를 구현해야함
public class FileConfig implements WebMvcConfigurer{
	
	@Value("${app.url.path}")
	private String url;
	
	@Value("${app.upload.location}")
	private String file;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler(url)
				.addResourceLocations(file);
		
	}
	
}
