package com.alekseyz.testtask.httploggerspringbootstarter.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;


public class PropertiesPostProcessor implements EnvironmentPostProcessor {

    private final PropertiesPropertySourceLoader propertySourceLoader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Resource resource = new ClassPathResource("log4j.properties");
        PropertySource<?> propertySource = loadProperty(resource);
        environment.getPropertySources().addLast(propertySource);
    }

    private PropertySource<?> loadProperty(Resource path) {
        Assert.isTrue(path.exists(), () -> "Resource " + path + " does not exist");
        try {
            List<PropertySource<?>> propertyList = propertySourceLoader.load("log4j", path);
            if (propertyList.isEmpty()) {
                throw new IllegalStateException("В файле конфигурации логгирования нет доступных для чтения параметров " + path);
            } else {
                return propertyList.get(0);
            }
        } catch (IOException ex1) {
            throw new IllegalStateException("Ошибка при чтении файла конфигурации по пути: " + path, ex1);
        }
    }
}
