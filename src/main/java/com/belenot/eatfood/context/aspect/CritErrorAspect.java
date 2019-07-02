package com.belenot.eatfood.context.aspect;

import com.belenot.eatfood.exception.ApplicationException;
import com.belenot.eatfood.service.DaoSqlService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@Aspect
public class CritErrorAspect {

    private ConfigurableWebApplicationContext ctx;

    public void setCtx(ConfigurableWebApplicationContext ctx) {
        this.ctx = ctx;
    }
    
    //    @Before("execution(* com.belenot.eatfood.service.DaoSqlService.*(..))" )
    public void initDaoError(JoinPoint jp) throws ApplicationException {
	if (jp.getTarget() instanceof DaoSqlService && !((DaoSqlService)jp.getTarget()).isConnected()) {
	    DaoSqlService daoSqlService = (DaoSqlService)jp.getTarget();
	    //log
	    throw new ApplicationException(String.format("Can't connection to database %s (%s, %s)", daoSqlService.getConnectionAddress(), daoSqlService.getUsername(), daoSqlService.getPassword()));
	}
    }
	
}
