package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMarksPage extends AbstractPage{

    public BookMarksPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".post.post_preview")
    public List<WebElement> postPreviewList;

    @FindBy(css = "footer>ul>li:nth-child(2)>button[title='Удалить из закладок']")
    public List<WebElement> bookmarkButtonIsAddedLocator;

    public void deletePostFromBookMarks(){
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2000)");
        this.bookmarkButtonIsAddedLocator.get(0).click();
    }

}
