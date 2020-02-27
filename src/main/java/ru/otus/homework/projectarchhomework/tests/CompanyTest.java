package ru.otus.homework.projectarchhomework.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.otus.homework.projectarchhomework.ProjectArchHomeworkApplication;
import ru.otus.homework.projectarchhomework.config.BaseWebDrivingTest;
import ru.otus.homework.projectarchhomework.config.Config;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.CompanyPage;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.MainPage;
import ru.otus.homework.projectarchhomework.services.auth.AuthorizationService;

@SpringBootTest(classes =  ProjectArchHomeworkApplication.class)
@ContextConfiguration(classes = Config.class)
@Test(groups = "smoke")
public class CompanyTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(MainPageTest.class);
    private Actions actions;
    private SoftAssert softAssert = new SoftAssert();
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private MainPage mainPage;
    @Autowired
    private CompanyPage companyPage;

    private static final String testCompany= "Сбербанк";

    @BeforeClass(alwaysRun = true)
    public void init(){
        actions = new Actions(driver);
        authorizationService.doLogin(config.getUrl(),config.getUsername(),config.getPassword());
        mainPage.goToNavPage(mainPage.mainPageTabsBlock.companyButton);
    }

    @Test(description = "Найти компанию - Сбербанк. Проверить, что компания найдена")
    public void searchCompany(){
        log.info("Ищем кампанию: [{}]", testCompany);
        companyPage.searchCompany(testCompany);
        Assert.assertEquals(companyPage.companiesList.size(),1,"Кампания ["+ testCompany +"] не найдена");
    }

    @Test(description = "Нажать кнопку 'Подписаться'. " +
            "Проверить, что при наведении кнопка меняет цвет на зеленый" +
            "Проверить, что после подписки отображается 'Подписан'",
        dependsOnMethods = "searchCompany")
    public void checkSubscription(){
        companyPage.subscribe("follow");
        log.info("Подписка на компанию [{}] оформлена", testCompany);
        actions.moveToElement(companyPage.companyRightGrid).build().perform();
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(companyPage.unsubscribeButton));
        String text = companyPage.unsubscribeButton.getText();
        softAssert.assertEquals(text, "Подписан", "Неверное название кнопки");
        softAssert.assertEquals(Color.fromString(companyPage.unsubscribeButton.getCssValue("background-color")).asHex(),"#689d29",
                "Неверный цвет кнопки - не зеленый");
        softAssert.assertAll();
    }

    @Test(description = "Нажать кнопку 'Отписаться'. Проверить, что после отписки отображается 'Подписаться'",
        dependsOnMethods = "checkSubscription", alwaysRun = true)
    public void checkUnSubscription(){
        companyPage.subscribe("unfollow");
        log.info("Подписка на компанию [{}] отменена", testCompany);
        actions.moveToElement(companyPage.companyRightGrid).build().perform();
        WebDriverWait wait = new WebDriverWait(driver,50L);
        wait.until(ExpectedConditions.visibilityOfAllElements(companyPage.subscribeButton));
        String text = companyPage.subscribeButton.getText();
        softAssert.assertEquals(text, "Подписаться", "Неверное название кнопки");
        System.out.println(Color.fromString(companyPage.subscribeButton.getCssValue("background-color")).asHex());
        softAssert.assertEquals(Color.fromString(companyPage.subscribeButton.getCssValue("background-color")).asHex(),"#000000",
                "Неверный цвет кнопки - не белый");
        softAssert.assertAll();
    }
}
