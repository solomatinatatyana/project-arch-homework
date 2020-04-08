package ru.otus.homework.projectarchhomework.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.otus.homework.projectarchhomework.ProjectArchHomeworkApplication;
import ru.otus.homework.projectarchhomework.config.BaseWebDrivingTest;
import ru.otus.homework.projectarchhomework.config.Config;
import ru.otus.homework.projectarchhomework.enums.SocialNetworks;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.LoginPage;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.MainPage;
import ru.otus.homework.projectarchhomework.services.auth.AuthorizationService;

@SpringBootTest(classes =  ProjectArchHomeworkApplication.class)
@ContextConfiguration(classes = Config.class)
@Epic("Spring Tests")
@Feature("Тесты с авторизацией")
@Story("Проверка авторизации пользователя. Положительный и негативный сценарии")
//@Test(groups = "smoke")
public class LoginTest extends BaseWebDrivingTest{

    private Logger log = LogManager.getLogger(LoginTest.class);
    private SoftAssert softAssert = new SoftAssert();
    private Actions actions;

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private MainPage mainPage;

    @DataProvider(name = "pictColors")
    public Object[][] SocialNetworks() {
        return new Object[][] {
                {loginPage.socialButtonsBlock.facebookButton,SocialNetworks.FACEBOOK.getColor()},
                {loginPage.socialButtonsBlock.vkButton,SocialNetworks.VK.getColor()},
                {loginPage.socialButtonsBlock.twitterButton,SocialNetworks.TWITTER.getColor()},
                {loginPage.socialButtonsBlock.githubButton,SocialNetworks.GITHUB.getColor()},
                {loginPage.socialButtonsBlock.liveIdButton,SocialNetworks.LIVE_ID.getColor()},
                {loginPage.socialButtonsBlock.googleButton,SocialNetworks.GOOGLE.getColor()}
        };}

    @BeforeClass(alwaysRun = true)
    public void init(){
        actions = new Actions(driver);
        authorizationService.goToLoginPage(config.getUrl());
    }

    @Test(description = "Проверить, что отображаются иконки всех социальных сетей и у каждой иконки при наведении свой цвет",
        dataProvider = "pictColors")
    public void checkSocialPictogram(WebElement button, String color) {
        softAssert.assertTrue(button.isDisplayed(),"икнока соц. сети: " + button.getText() + " не отображается");
        moveToElement(button);
        String colorCurrent = getColorOfElement(button);
        softAssert.assertEquals(colorCurrent, color, "Неверный цвет у элемента: " + button.getText());
        softAssert.assertAll();
    }


    @Test(description = "Проверить успешную авторизацию", alwaysRun = true, dependsOnMethods = "checkSocialPictogram")
    public void checkLoginSuccess() {
        log.info("Логинимся по юзером {}", config.getUsername());
        authorizationService.doLogin(config.getUrl(),config.getUsername(), config.getPassword());
        mainPage.openProfileMenu();
        Assert.assertTrue(mainPage.profileSettingBlock.profileInfo.getText().contains("Solomka95"),
                "Неверное название профиля");
    }

    @Test(description = "Проверить неуспешную авторизацию",
            dependsOnMethods = "checkLoginSuccess",alwaysRun = true)
    public void checkLoginFail() {
        mainPage.userProfileButton.click();
        log.info("Разлогиниваемся");
        authorizationService.logout();
        log.info("Логинимся с неверным паролем");
        authorizationService.doLogin(config.getUsername(), "123");
        WebDriverWait wait = new WebDriverWait(driver,100L);
        wait.until(ExpectedConditions.visibilityOfAllElements(loginPage.username));
        softAssert.assertTrue(loginPage.noticeElement.isDisplayed(),"Плашка с ошибкой не отобразилась");
        softAssert.assertEquals(Color.fromString(loginPage.noticeElement.getCssValue("background-color")).asHex(),"#dd6c6c",
                "Плашка с ошибкой не красного цвета");
        softAssert.assertEquals(loginPage.noticeElement.findElement(By.tagName("div")).getText(),
                "Пользователь с такой электронной почтой или паролем не найден",
                "Неверный текст ошибки");
        softAssert.assertAll();
    }
//--------------------------------------------------METHODS-------------------------------------------------------------
    private String getColorOfElement(WebElement element){
        return Color.fromString(element.getCssValue("background-color")).asHex();
    }

    private void moveToElement(WebElement element){
        actions.moveToElement(element).build().perform();
    }
}
