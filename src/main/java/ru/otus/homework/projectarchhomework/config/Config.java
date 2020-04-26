package ru.otus.homework.projectarchhomework.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.homework.projectarchhomework.config.ui.BrowserType;
import ru.otus.homework.projectarchhomework.services.driver.WebApplicationService;

import java.util.concurrent.TimeUnit;

@PropertySource("classpath:properties/application.properties")
@Configuration
@ComponentScan
public class Config {
    protected WebDriver driver;
    protected MutableCapabilities options;
    protected static String browser = System.getProperty("browser").toUpperCase();

    @Value("${sut.url}")
    private String url;

    @Value("${site.login}")
    private String username;

    @Value("${site.password}")
    private String password;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    private WebApplicationService webApplicationService;

    @Bean
    public WebDriver getDriver() {
        this.options = new MutableCapabilities();
        this.driver = webApplicationService.initDriver(BrowserType.valueOf(browser), options);
        this.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        this.driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        return this.driver;
    }
}
