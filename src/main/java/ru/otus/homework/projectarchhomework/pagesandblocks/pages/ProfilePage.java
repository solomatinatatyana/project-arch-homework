package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ProfilePage extends AbstractPage {
    private Logger log = LogManager.getLogger(ProfilePage.class);

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public By userAvatarUpl = By.cssSelector(".user-avatar__image");

    @FindBy(xpath = ".//div[a[contains(text(),'Настроить профиль')]]")
    public WebElement editProfileButton;

    @FindBy(css = ".tm-button.tm-button_submit.h-form__submit-button")
    public WebElement saveButton;

    @FindBy(css = "input[type='file']")
    public WebElement uploadFileInput;

    @FindBy(xpath = ".//button[span[contains(text(),'Удалить')]]")
    public WebElement deleteButton;

    @FindBy(css = ".form-fileupload__button")
    public WebElement uploadForm;

    @FindBy(css = ".user-avatar")
    public WebElement userAvatar;

    @FindBy(css = ".user-avatar__image")
    public WebElement userAvatarUploaded;

    public void uploadPhoto(String path) {
        WebDriverWait wait = new WebDriverWait(driver, 50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(this.uploadForm));
        File file = new File(path);
        this.uploadFileInput.sendKeys(file.getAbsolutePath());
        wait.until(ExpectedConditions.visibilityOfAllElements(this.userAvatar));
        log.info("Аватар успешно загружен");
    }

    @Step("Сохранить изменения")
    public void save() {
        this.saveButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 50L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message.message_successfull")));
    }

    @Step("Удалить фото профиля")
    public void deletePhoto() {
        WebDriverWait wait = new WebDriverWait(driver, 50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(this.deleteButton));
        this.deleteButton.click();
        log.info("Картинка успешно удалена");
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
