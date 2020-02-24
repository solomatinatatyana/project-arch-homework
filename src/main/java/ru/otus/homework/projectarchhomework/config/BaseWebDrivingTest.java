package ru.otus.homework.projectarchhomework.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.otus.homework.projectarchhomework.config.ui.BrowserType;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.LoginPage;

/**
 * Класс для запуска тестов с использованием браузера
 */
public class BaseWebDrivingTest extends BaseTest {
    protected WebDriver driver;

    @Autowired
    protected Config config;

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    protected  static String browser = System.getProperty("browser").toUpperCase();

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        super.setUp();
        log.info("Test: [{}]",this.getClass().asSubclass(this.getClass()).getSimpleName());
        log.info("Browser: [{}]", BrowserType.valueOf(browser));
        this.setDriver(config.getDriver());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        log.info("END of TEST - {}",getClass().asSubclass(getClass().getSuperclass()).getSimpleName());
        if (driver != null) {
            driver.quit();
        }
    }
}
