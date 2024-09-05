package com.sse.app.aops.pays;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Transaction {

	
	@AfterThrowing("execution(* com.sse.app.*.*.add(..))")
	public void rollBack() {
		
	}
	
}
