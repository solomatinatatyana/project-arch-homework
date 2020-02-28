package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.blocks.LanguageSettingsBlock;
import ru.otus.homework.projectarchhomework.pagesandblocks.blocks.MainNavBarBlock;
import ru.otus.homework.projectarchhomework.pagesandblocks.blocks.MainPageTabsBlock;
import ru.otus.homework.projectarchhomework.pagesandblocks.blocks.ProfileSettingBlock;

import java.util.List;


@Component
public class MainPage extends AbstractPage{

    @Autowired
    public ProfileSettingBlock profileSettingBlock;
    @Autowired
    public MainPageTabsBlock mainPageTabsBlock;
    @Autowired
    public MainNavBarBlock mainNavBarBlock;
    @Autowired
    public LanguageSettingsBlock languageSettingsBlock;

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

    @FindBy(css = ".post-stats__result>span:nth-child(2)")
    public List<WebElement> postsStatsList;

    @FindBy(css = ".loader")
    public WebElement loader;

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

    public void filterPosts(WebElement filterElement){
        WebElement button = (new WebDriverWait(driver, 50L))
                .until(ExpectedConditions.elementToBeClickable(filterElement));
        button.click();
    }

    public void goToNavPage(WebElement page){
        WebElement button = (new WebDriverWait(driver, 50L))
                .until(ExpectedConditions.elementToBeClickable(page));
        button.click();
    }

    public void changeInterfaceLanguage(WebElement language){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(this.languageSettingsBlock.headerInterface));
        language.click();
    }

    public void saveSettings(){
        this.languageSettingsBlock.saveButton.click();
        ((JavascriptExecutor) driver).executeScript("window.location.reload()");
    }

}
