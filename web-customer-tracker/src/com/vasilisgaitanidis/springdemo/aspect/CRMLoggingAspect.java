package com.vasilisgaitanidis.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.vasilisgaitanidis.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.vasilisgaitanidis.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.vasilisgaitanidis.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void beforeAspect(JoinPoint theJoinPoint) {
		
		// print calling method
		String callingMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info(">>> in @Before: calling method: " + callingMethod);
		
		// getting args from the JoinPoint
		Object[] args = theJoinPoint.getArgs();
		
		for (Object tempArg: args) {
			// displaying the returning value info of the calling method
			myLogger.info("Argument: " + tempArg);
		}
		
	}
	
	// add @AfterReturning advice

}
