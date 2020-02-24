package ru.otus.homework.projectarchhomework.services.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import ru.otus.homework.projectarchhomework.config.ui.BrowserType;

public interface WebApplicationService {
    WebDriver initDriver(BrowserType browser, MutableCapabilities options);
}
