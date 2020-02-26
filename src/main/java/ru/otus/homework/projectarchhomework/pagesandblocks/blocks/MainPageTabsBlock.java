package ru.otus.homework.projectarchhomework.pagesandblocks.blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.AbstractPage;

@Component
public class MainPageTabsBlock extends AbstractPage {

    public MainPageTabsBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//a[h3[contains(text(),'Статьи')]]")
    public WebElement postsButton;

    @FindBy(xpath = ".//a[h3[contains(text(),'Новости')]]")
    public WebElement newsButton;

    @FindBy(xpath = ".//a[h3[contains(text(),'Хабы')]]")
    public WebElement habsButton;

    @FindBy(xpath = ".//a[h3[contains(text(),'Авторы')]]")
    public WebElement authorsButton;

    @FindBy(xpath= ".//a[h3[contains(text(),'Компании')]]")
    public WebElement companyButton;

    @FindBy(xpath = ".//a[contains(text(),'Все подряд')]")
    public WebElement allPostsButton;

    @FindBy(xpath = ".//a[contains(text(),'Лучшие')]")
    public WebElement bestPostsButton;

    @FindBy(xpath = ".//a[contains(text(),'Без порога')]")
    public WebElement noFilterButton;

    @FindBy(css = "a[title='Все публикации с рейтингом 10 и выше']")
    public WebElement more10Button;

    @FindBy(css = "a[title='Все публикации с рейтингом 25 и выше']")
    public WebElement more25Button;

    @FindBy(css = "a[title='Все публикации с рейтингом 50 и выше']")
    public WebElement more50Button;

    @FindBy(css = "a[title='Все публикации с рейтингом 100 и выше']")
    public WebElement more100Button;


}
