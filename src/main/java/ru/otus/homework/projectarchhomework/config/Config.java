package ru.otus.homework.projectarchhomework.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("classpath:properties/application.properties")
public class Config {

    @Value("${sut.url}")
    private String url;

    public String getUrl() {
        return url;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /*@Autowired
    private DriverInitService driverInitService;

    @Bean
    public WebDriver getDriver() {return driverInitService.initDriver()}*/
}
