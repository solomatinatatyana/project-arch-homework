package ru.otus.homework.projectarchhomework.config;

import com.google.inject.Inject;
import com.google.inject.internal.cglib.proxy.$MethodInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import ru.otus.homework.projectarchhomework.config.injection.interfaces.UIConfig;

/**
 * Безовый класс для запуска тестов. Содержит настройки логирования и другие общие настройки
 */
public class BaseTest extends AbstractTestNGSpringContextTests {
    protected static Logger log;

    /*@Inject
    UIConfig uiConfig;*/

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        log = LogManager.getLogger("TestRunner");
    }
}
