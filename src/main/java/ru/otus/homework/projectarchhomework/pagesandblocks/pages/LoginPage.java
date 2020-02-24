package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends AbstractPage{
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**Кнопка "Войти"*/
    @FindBy(css = "a[id='login']")
    public WebElement signButton;

    /**Поле "Login"*/
    @FindBy(css = "input[type=email]")
    public WebElement username;

    /**Поле "Password"*/
    @FindBy(css = "input[type=password]")
    public WebElement pass;

    /**Кнопка "Войти"*/
    @FindBy(css = "button[name='go']")
    public WebElement submit;

    @FindBy(css = "div.notice.error")
    public WebElement noticeElement;

}
