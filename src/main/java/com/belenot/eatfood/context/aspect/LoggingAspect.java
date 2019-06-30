package com.belenot.eatfood.context.aspect;


import com.belenot.eatfood.exception.ApplicationException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;



@Aspect
public class LoggingAspect {
    
    @AfterThrowing( throwing = "exc", pointcut = "execution(* com.belenot.eatfood.web.controller.*.*(..))" )
    public void ControllerApplicationExceptionThrowed(JoinPoint jp, ApplicationException exc) throws ApplicationException {
	Logger logger = LogManager.getLogger(jp.getTarget().getClass());
	logger.warn(exc.getMessage(), exc);
	throw exc;
    }
}
