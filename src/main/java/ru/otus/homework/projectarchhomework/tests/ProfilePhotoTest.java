package ru.otus.homework.projectarchhomework.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.otus.homework.projectarchhomework.ProjectArchHomeworkApplication;
import ru.otus.homework.projectarchhomework.config.BaseWebDrivingTest;
import ru.otus.homework.projectarchhomework.config.Config;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.MainPage;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.ProfilePage;
import ru.otus.homework.projectarchhomework.services.auth.AuthorizationService;

@SpringBootTest(classes =  ProjectArchHomeworkApplication.class)
@ContextConfiguration(classes = Config.class)
@Epic("Spring Tests")
@Feature("Тесты с авторизацией")
@Story("Проверка добавления фотографии в профиль. Позитивный и негативный сценарии")
@Test(groups = "smoke")
public class ProfilePhotoTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(ProfilePhotoTest.class);
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private MainPage mainPage;
    @Autowired
    private ProfilePage profilePage;

    @BeforeClass(alwaysRun = true)
    public void init(){
        authorizationService.doLogin(config.getUrl(), config.getUsername(),config.getPassword());
        mainPage.openProfileMenu();
        mainPage.profileSettingBlock.profileInfo.click();
        profilePage.editProfileButton.click();
    }

    @Description("Загрузить аватар допустимого размера до 96х96. Проверить, что аватар загружен")
    @Test()
    public void uploadCorrectPhoto(){
        log.info("Загружаем аватар допустимого размера...");
        profilePage.uploadPhoto("src/main/resources/images/success.png");
        profilePage.save();
        mainPage.openProfileMenu();
        mainPage.profileSettingBlock.bookmarkButton.click();
        mainPage.openProfileMenu();
        mainPage.profileSettingBlock.profileInfo.click();
        profilePage.editProfileButton.click();
        Assert.assertTrue(profilePage.isElementPresent(profilePage.userAvatarUpl),"Аватар не загрузился");
    }

    @Description("Загрузить недопустимый аватар. Проверить, что аватар не загрузился")
    @Test(dependsOnMethods = "uploadCorrectPhoto", alwaysRun = true)
    public void uploadUnCorrectPhoto(){
        profilePage.deletePhoto();
        profilePage.save();
        log.info("Загружаем аватару недопустимого размера...");
        profilePage.uploadPhoto("src/main/resources/images/fail.png");
        Assert.assertFalse(profilePage.isElementPresent(profilePage.userAvatarUpl),"Аватар загрузился");
    }
}
