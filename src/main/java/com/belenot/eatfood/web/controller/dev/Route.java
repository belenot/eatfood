package com.belenot.eatfood.web.controller.dev;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;


@Controller
@Profile("dev")
public class Route {

    @Value("classpath:static/*")
    private List<Resource> resources;
    @Autowired
    private WebApplicationContext wac;

    @GetMapping(value="/resources")
    @ResponseBody
    public String resources() {
        StringBuilder builder = new StringBuilder();
        for (Resource resource : resources) {
            builder.append(resource.getFilename()).append("<br/>");
        }
        return builder.toString();
    }
    
}