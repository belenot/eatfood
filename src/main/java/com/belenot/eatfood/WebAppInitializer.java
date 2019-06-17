package com.belenot.eatfood;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.belenot.eatfood.context.WebAppContext;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	ctx.register(WebAppContext.class);
	ServletRegistration.Dynamic dispatcher = servletContext.addServlet("eatfood", new DispatcherServlet(ctx));
	dispatcher.setLoadOnStartup(1);
	dispatcher.addMapping("/");
    }
}

