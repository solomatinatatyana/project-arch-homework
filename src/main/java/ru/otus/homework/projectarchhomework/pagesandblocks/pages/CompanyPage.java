package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.blocks.CompanySideBarBlock;

import java.util.List;

@Component
public class CompanyPage extends AbstractPage{

    @Autowired
    public CompanySideBarBlock companySideBarBlock;

    public CompanyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    By companies = By.cssSelector("ul[id='companies']>li");

    @FindBy(id = "companies_suggest")
    public WebElement searchCompanyTextInput;

    @FindBy(css = "ul[id='companies']>li")
    public List<WebElement> companiesList;

    @FindBy(css = ".table-grid__item.table-grid__item_right")
    public WebElement companyRightGrid;

    @FindBy(css = "button[data-state='follow']")
    public WebElement subscribeButton;

    @FindBy(css = "button[data-state='unfollow']")
    public WebElement unsubscribeButton;


    public void searchCompany(String company){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(this.searchCompanyTextInput));
        searchCompanyTextInput.clear();
        searchCompanyTextInput.sendKeys(company);
        WebDriverWait wait2 = new WebDriverWait(driver,50L);
        wait2.until(ExpectedConditions.numberOfElementsToBe(this.companies,1));
    }

    /**
     * Подписаться/отписаться
     */
    public void subscribe(String actions){
        Actions action = new Actions(driver);
        action.moveToElement(companyRightGrid).build().perform();
        switch (actions){
            case "follow":
                WebDriverWait wait = new WebDriverWait(driver,50L);
                wait.until(ExpectedConditions.visibilityOfAllElements(this.subscribeButton));
                action.moveToElement(subscribeButton).click().build().perform();
                break;
            case "unfollow":
                WebDriverWait wait2 = new WebDriverWait(driver,50L);
                wait2.until(ExpectedConditions.visibilityOfAllElements(this.unsubscribeButton));
                action.moveToElement(unsubscribeButton).click().build().perform();
                break;

        }
    }

    public void filterCompanyByTopic(WebElement topicButton){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(topicButton));
        topicButton.click();
    }

    public int getCountCompaniesByTopic(WebElement topicButton){
        return  Integer.parseInt(topicButton.findElement(this.companySideBarBlock.countTopics).getText());
    }

}
