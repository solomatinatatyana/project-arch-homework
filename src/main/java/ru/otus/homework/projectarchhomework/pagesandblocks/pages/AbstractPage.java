package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import org.openqa.selenium.WebDriver;


public abstract class AbstractPage {
    public WebDriver driver;
    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }
}
