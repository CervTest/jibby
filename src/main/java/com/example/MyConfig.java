package com.example;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

@Singleton
public class MyConfig {

    private final String appName;

    public MyConfig(@Value("${micronaut.application.name}") String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }
}
