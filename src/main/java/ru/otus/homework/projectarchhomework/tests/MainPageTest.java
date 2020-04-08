package ru.otus.homework.projectarchhomework.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
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
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.MainPage;
import ru.otus.homework.projectarchhomework.services.auth.AuthorizationService;

@SpringBootTest(classes =  ProjectArchHomeworkApplication.class)
@ContextConfiguration(classes = Config.class)
@Epic("Spring Tests")
@Feature("Тесты без авторизации")
@Story("Проверка фильтрации постов")
//@Test(groups = "smoke")
public class MainPageTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(MainPageTest.class);
    private SoftAssert softAssert = new SoftAssert();
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private MainPage mainPage;

    @DataProvider(name = "filters")
    public Object[][] Filters() {
        return new Object[][] {
                {mainPage.mainPageTabsBlock.more10Button, 10},
                {mainPage.mainPageTabsBlock.more25Button, 25},
                {mainPage.mainPageTabsBlock.more50Button, 50},
                {mainPage.mainPageTabsBlock.more100Button, 100}
        };}

    @BeforeClass(alwaysRun = true)
    public void init(){
        authorizationService.open(config.getUrl());
        mainPage.goToNavPage(mainPage.mainNavBarBlock.allButton);
    }

    @Description("Проверить, что на странице отображается 20 постов")
    @Test()
    public void checkCountPosts(){
        log.info("Провера количетсва постов на странице...");
        Assert.assertEquals(mainPage.postPreviewList.size(),20,
                "На странице неверное количество постов");

    }
    // >=10, >=25, >=50, >=100
    @Description("Отфильтровать посты по рейтингу. " +
            "Проверить, что отображаются посты в соответствии с установленным фильтром")
    @Test(dependsOnMethods = "checkCountPosts", dataProvider = "filters",
            alwaysRun = true)
    public void checkRatingInPosts(WebElement filterButton, int count){
        log.info("Проверка фильтра [{}]",filterButton.getText());
        mainPage.filterPosts(filterButton);
        mainPage.postsStatsList.forEach(e->{
            int result = Integer.parseInt(e.getText());
            softAssert.assertTrue(result >= count, "Отобрались посты с рейтингом меньше " + count);
        });
        softAssert.assertAll();
    }
}
