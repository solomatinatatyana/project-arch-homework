package ru.otus.homework.projectarchhomework.pagesandblocks.blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.AbstractPage;

import java.util.List;

@Component
public class SocialButtonsBlock extends AbstractPage {
    public SocialButtonsBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".socials-buttons.s-buttons>a")
    public List<WebElement> socialButtons;

    @FindBy(css = "a[title='Зарегистрироваться с помощью Facebook']")
    public WebElement facebookButton;

    @FindBy(css = "a[title='Зарегистрироваться с помощью Вконтакте']")
    public WebElement vkButton;

    @FindBy(css = "a[title='Зарегистрироваться с помощью Twitter']")
    public WebElement twitterButton;

    @FindBy(css = "a[title='Зарегистрироваться с помощью Github']")
    public WebElement githubButton;

    @FindBy(css = "a[title='Зарегистрироваться с помощью LiveID']")
    public WebElement liveIdButton;

    @FindBy(css = "a[title='Зарегистрироваться с помощью Google']")
    public WebElement googleButton;
}

