package com.alekseyz.testtask.httploggerspringbootstarter.config;


import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "http-logger-spring-boot-starter")
public class HttpLoggerStarterProperties {

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
