package com.belenot.eatfood.context.aspect;

import com.belenot.eatfood.exception.ApplicationException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@Aspect
public class CritErrorAspect {

    private ConfigurableWebApplicationContext ctx;

    public void setCtx(ConfigurableWebApplicationContext ctx) {
        this.ctx = ctx;
    }
    
    @AfterThrowing( throwing = "exc", pointcut = "execution(* com.belenot.eatfood.dao.*.*(..))" )
    public void initDaoError(JoinPoint jp, NullPointerException exc) throws ApplicationException {
	throw new ApplicationException("Can't get access to database", exc);
    }
	
}
