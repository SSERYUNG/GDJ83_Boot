package com.sse.app.configs;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer{

//	spring pool에 담아놨다가 필요할 때 보내주려면 이 어노테이션이 있어야함
//	이 메서드명은 무조건 localeResolver << 안 그러면 에러날 수도
	@Bean
	public LocaleResolver localeResolver() {
//		1.Session을 이용하기
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN); //기본언어설정
		
//		2.Cookie를 이용하기
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		
//		얘가 메시지를 무슨 언어로 할 지 정해주는 애?
		return cookieLocaleResolver;
	}
	
//	언어 전용 인터셉터가 있음
	@Bean
	LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor chaneInterceptor = new LocaleChangeInterceptor();
		chaneInterceptor.setParamName("lang");
//		파라미터를 받아서 언어를 구분하겠다 ex url?lang=ko
		
		return chaneInterceptor;
	}
	
}
