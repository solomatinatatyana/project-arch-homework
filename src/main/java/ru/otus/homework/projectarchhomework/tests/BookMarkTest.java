package ru.otus.homework.projectarchhomework.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.otus.homework.projectarchhomework.ProjectArchHomeworkApplication;
import ru.otus.homework.projectarchhomework.config.BaseWebDrivingTest;
import ru.otus.homework.projectarchhomework.config.Config;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.BookMarksPage;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.MainPage;
import ru.otus.homework.projectarchhomework.services.auth.AuthorizationService;

@SpringBootTest(classes =  ProjectArchHomeworkApplication.class)
@ContextConfiguration(classes = Config.class)
@Epic("Spring Tests")
@Feature("Тесты с авторизацией")
@Story("Проверка добавления закладок в профиле пользователя")
@Test(groups = "smoke")
public class BookMarkTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(BookMarkTest.class);
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private MainPage mainPage;
    @Autowired
    private BookMarksPage bookMarksPage;

    @BeforeClass(alwaysRun = true)
    public void init(){
        /*Авторизоваться на сайте*/
        authorizationService.doLogin(config.getUrl(),config.getUsername(),config.getPassword());
    }

    @Test(description = "Найти несколько статей по Spring. Проверить, что статьи нашлись.")
    public void searchPosts(){
        /*Выполнить поиск статей*/
        mainPage.doSearch("Spring");
        /*Проверить, что найденные статьи соответствуют поисковому запросу*/
        mainPage.postPreviewList.forEach(e->{
            String text = e.findElement(By.cssSelector("h2>a")).getText();
            Assert.assertTrue(text.contains("Spring"),"В найденных постах нет упоминания Spring");
        });
    }

    @Test(description = "Добавить 1 пост в закладки. Проверить, что посты отображаются в закладках.",
        dependsOnMethods = "searchPosts")
    public void addBookMarks(){
        /*Добавить в закладки первые два поста*/
        mainPage.addPostToBookMarks();
        /*Перейти на страницу с закладками*/
        mainPage.openProfileMenu();
        mainPage.profileSettingBlock.bookmarkButton.click();
        /*Проверить, что посты в закладки добавлены*/
        Assert.assertEquals(bookMarksPage.postPreviewList.size(),1,
                "Добавлено неверное количество постов в закладки");
    }

    @Test(description = "Удалить пост из закладок. Проверить, что в закладках не осталось постов",
            dependsOnMethods = "addBookMarks")
    public void deleteBookMark(){
        /*Удалить пост из закладок*/
        bookMarksPage.deletePostFromBookMarks();
        ((JavascriptExecutor) driver).executeScript("window.location.reload()");
        /*Проверить, что в закладках не осталось постов*/
        Assert.assertEquals(bookMarksPage.postPreviewList.size(),0,
                "Осталось неверное количество постов в закладках");
    }
}
