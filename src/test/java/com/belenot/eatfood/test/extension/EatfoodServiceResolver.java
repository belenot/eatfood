package com.belenot.eatfood.test.extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.belenot.eatfood.context.WebAppContext;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class EatfoodServiceResolver implements ParameterResolver, AfterAllCallback {


    private ConfigurableWebApplicationContext ctx;
    
    @Retention( RetentionPolicy.RUNTIME )
    @Target( ElementType.PARAMETER )
    public @interface EatfoodService { };
    
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
	throws ParameterResolutionException {
	return parameterContext.isAnnotated(EatfoodService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
	throws ParameterResolutionException {
	return getService(parameterContext);
    }
    
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
	if (ctx != null && ctx.isActive()) {
	    ctx.close();
	    ctx = null;
	}
    }

    private Object getService(ParameterContext parameter) {
	ctx = getCtx();
	try {
	    return ctx.getBean(parameter.getParameter().getType());
	} catch (Exception exc) {
	    throw new ParameterResolutionException("Can't fetch service " + parameter.getParameter().getType());
	}
    }

    private ConfigurableWebApplicationContext getCtx() {
	if (ctx != null && ctx.isActive()) return ctx;
	ctx = new AnnotationConfigWebApplicationContext();
	ctx.setServletContext(new MockServletContext());
	((AnnotationConfigWebApplicationContext)ctx).register(WebAppContext.class);
	ctx.refresh();
	return ctx;
    }
}
