package ru.otus.homework.projectarchhomework.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.otus.homework.projectarchhomework.ProjectArchHomeworkApplication;
import ru.otus.homework.projectarchhomework.config.BaseWebDrivingTest;
import ru.otus.homework.projectarchhomework.config.Config;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.LoginPage;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.MainPage;
import ru.otus.homework.projectarchhomework.services.auth.AuthorizationService;

@SpringBootTest(classes =  ProjectArchHomeworkApplication.class)
@ContextConfiguration(classes = Config.class)
@Test(groups = "smoke")
public class LoginTest extends BaseWebDrivingTest{

    private Logger log = LogManager.getLogger(LoginTest.class);
    private SoftAssert softAssert = new SoftAssert();

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private MainPage mainPage;


    @Test(description = "Проверить успешную авторизацию")
    public void checkLoginSuccess() {
        authorizationService.doLogin(config.getUrl(),config.getUsername(), config.getPassword());
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(mainPage.userProfileButton));
        mainPage.userProfileButton.click();
        Assert.assertTrue(mainPage.profileSettingBlock.profileInfo.getText().contains("Solomka95"),
                "Неверное название профиля");
    }

    @Test(description = "Проверить неуспешную авторизацию",
            dependsOnMethods = "checkLoginSuccess",alwaysRun = true)
    public void checkLoginFail() {
        mainPage.userProfileButton.click();
        authorizationService.logout();
        WebDriverWait wait = new WebDriverWait(driver,100L);
        wait.until(ExpectedConditions.visibilityOfAllElements(loginPage.signButton));
        authorizationService.doLogin(config.getUsername(), "123");
        WebDriverWait wait2 = new WebDriverWait(driver,100L);
        wait2.until(ExpectedConditions.visibilityOfAllElements(loginPage.username));
        softAssert.assertTrue(loginPage.noticeElement.isDisplayed(),"Плашка с ошибкой не отобразилась");
        softAssert.assertEquals(Color.fromString(loginPage.noticeElement.getCssValue("background-color")).asHex(),"#dd6c6c",
                "Плашка с ошибкой не красного цвета");
        softAssert.assertEquals(loginPage.noticeElement.findElement(By.tagName("div")).getText(),
                "Пользователь с такой электронной почтой или паролем не найден",
                "Неверный текст ошибки");
        softAssert.assertAll();
    }
}
