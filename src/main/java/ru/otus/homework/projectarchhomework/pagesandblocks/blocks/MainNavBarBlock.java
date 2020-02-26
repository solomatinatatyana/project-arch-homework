package ru.otus.homework.projectarchhomework.pagesandblocks.blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.AbstractPage;

@Component
public class MainNavBarBlock extends AbstractPage {
    public MainNavBarBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//a[contains(text(),'Все потоки')]")
    public WebElement allButton;

    @FindBy(xpath = ".//a[contains(text(),'Разработка')]")
    public WebElement developmentButton;

    @FindBy(xpath = ".//a[contains(text(),'Научпоп')]")
    public WebElement popsciButton;

    @FindBy(xpath = ".//a[contains(text(),'Администрирование')]")
    public WebElement adminButton;

    @FindBy(xpath = ".//a[contains(text(),'Дизайн')]")
    public WebElement designButton;

    @FindBy(xpath = ".//a[contains(text(),'Менеджмент')]")
    public WebElement managementButton;

    @FindBy(xpath = ".//a[contains(text(),'Маркетинг')]")
    public WebElement marketingButton;

}
