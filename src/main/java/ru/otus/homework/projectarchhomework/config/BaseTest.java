package ru.otus.homework.projectarchhomework.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;
import ru.otus.homework.projectarchhomework.ProjectArchHomeworkApplication;
import ru.otus.homework.projectarchhomework.config.interfaces.UIConfig;

/**
 * Безовый класс для запуска тестов. Содержит настройки логирования и другие общие настройки
 */
public class BaseTest {
    protected static Logger log;

    /*@Autowired
    protected Config uiConfig;*/

    @BeforeClass(alwaysRun = true)
    public void setUp(){

        log = LogManager.getLogger("TestRunner");
    }
}
