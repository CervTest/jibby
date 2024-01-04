package com.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;

@Controller("/hello")
public class HelloController {
    @Inject
    private final MyConfig myConfig;

    public HelloController(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/")
    public String index() {
        return "Hello Kubernetes World - now on Micronaut v3 and in Java, huzzah! And extra Docker goodness plus env specifics: "  + myConfig.getAppName();
    }
}
