package ru.otus.homework.projectarchhomework.config.ui;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebApplicationManager {
    private WebDriver driver;
    private MutableCapabilities options;

    public WebDriver getDriver(){
        return this.driver;
    }

    public WebApplicationManager() { }

    public WebApplicationManager(BrowserType browserType, MutableCapabilities options) {
        this.options = options;
        this.driver = WebDriverFactory.createNewDriver(browserType, options);
        this.init();
    }

    private void init(){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
