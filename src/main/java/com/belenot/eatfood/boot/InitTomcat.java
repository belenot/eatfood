package com.belenot.eatfood.boot;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class InitTomcat {
    public static void main(String[] args) throws Exception {
	//Declare properties
	String contextPath = "/eatfood";
	String docBase = new File("src/main/webapp").getAbsolutePath();
	String additionWebInfClasses = new File("target/classes").getAbsolutePath();
	//Init tomcat
	Tomcat tomcat = new Tomcat();
	tomcat.setPort(8080);
	tomcat.setBaseDir("tomcat");
        Context context = tomcat.addWebapp(contextPath, docBase);
	WebResourceRoot resources = new StandardRoot(context);
	resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses, "/"));
	context.setResources(resources);
	tomcat.start();
	tomcat.getServer().await();
    }
}
