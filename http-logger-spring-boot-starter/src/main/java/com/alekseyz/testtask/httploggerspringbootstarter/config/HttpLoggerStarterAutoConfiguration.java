package com.alekseyz.testtask.httploggerspringbootstarter.config;


import com.alekseyz.testtask.httploggerspringbootstarter.logging.HttpLoggingInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableConfigurationProperties(HttpLoggerStarterProperties.class)
@ConditionalOnProperty(prefix = "http-logger-spring-boot-starter", name = "enabled", havingValue = "true")
public class HttpLoggerStarterAutoConfiguration {

    @Bean
    public HttpLoggingInterceptor httpLoggingInterceptor(){
        return new HttpLoggingInterceptor();
    }

    @Bean
    public WebMvcConfigurer loggerConfig(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new LoggerConfig(httpLoggingInterceptor);
    }

}
