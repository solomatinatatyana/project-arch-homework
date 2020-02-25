package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.blocks.ProfileSettingBlock;

import java.util.List;


@Component
public class MainPage extends AbstractPage{

    @Autowired
    public ProfileSettingBlock profileSettingBlock;
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn_navbar_user-dropdown")
    public WebElement userProfileButton;

    @FindBy(id = "search-form-btn")
    public WebElement searchButton;

    @FindBy(id = "search-form-field")
    public WebElement searchTextInput;

    @FindBy(css = ".post_preview")
    public List<WebElement> postPreviewList;

    @FindBy(css = "footer>ul>li:nth-child(2)>button[title='Добавить в закладки']")
    public List<WebElement> bookmarkNotAddedButton;

    public void doSearch(String text){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(this.searchButton));
        searchButton.click();
        searchTextInput.clear();
        searchTextInput.sendKeys(text);
        searchTextInput.sendKeys(Keys.ENTER);
    }

    public void addPostToBookMarks(){
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2000)");
        this.bookmarkNotAddedButton.get(0).click();
        WebElement lol = (new WebDriverWait(driver, 50L))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".jGrowl-notification.highlight.ui-corner-all.default>div.jGrowl-close")));
        lol.click();
    }

    public void openProfileMenu(){
        WebElement button = (new WebDriverWait(driver, 50L))
                .until(ExpectedConditions.elementToBeClickable(this.userProfileButton));
        button.click();
    }
}
