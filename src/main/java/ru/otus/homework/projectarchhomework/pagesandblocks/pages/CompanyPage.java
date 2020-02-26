package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyPage extends AbstractPage{
    public CompanyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "companies_suggest")
    public WebElement searchCompanyTextInput;

    @FindBy(id = "companies")
    public List<WebElement> companiesList;


    public void searchCompany(String company){
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(this.searchCompanyTextInput));
        searchCompanyTextInput.clear();
        searchCompanyTextInput.sendKeys(company);
    }

}
