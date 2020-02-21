package ru.otus.homework.projectarchhomework;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testng.annotations.Test;
import ru.otus.homework.projectarchhomework.config.BaseWebDrivingTest;
import ru.otus.homework.projectarchhomework.config.Config;

@SpringBootTest()
@ContextConfiguration(classes = Config.class)
@TestPropertySource
public class ProjectArchHomeworkApplicationTests /*extends BaseWebDrivingTest */{
    @Autowired
    Config config;

    @Test()
    public void contextLoads() {
        System.out.println(config.getUrl());
        //driver.get("https://habr.com/ru");

        //driver.get(uiConfig.url);
    }

}
