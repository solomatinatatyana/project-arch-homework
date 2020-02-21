package ru.otus.homework.projectarchhomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.homework.projectarchhomework.config.Config;

@SpringBootApplication
public class ProjectArchHomeworkApplication{

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(ProjectArchHomeworkApplication.class, args);

        //System.out.println(config.getUrl());

    }

}
