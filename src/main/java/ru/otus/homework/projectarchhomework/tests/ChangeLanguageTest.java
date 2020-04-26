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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.otus.homework.projectarchhomework.ProjectArchHomeworkApplication;
import ru.otus.homework.projectarchhomework.config.BaseWebDrivingTest;
import ru.otus.homework.projectarchhomework.config.Config;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.MainPage;
import ru.otus.homework.projectarchhomework.services.auth.AuthorizationService;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = ProjectArchHomeworkApplication.class)
@ContextConfiguration(classes = Config.class)
@Epic("Spring Tests")
@Feature("Тесты с авторизацией")
@Story("Проверка смены языка профиля пользователя")
@Test(groups = "smoke")
public class ChangeLanguageTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(ChangeLanguageTest.class);
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private MainPage mainPage;

    @BeforeClass(alwaysRun = true)
    public void init() {
        authorizationService.doLogin(config.getUrl(), config.getUsername(), config.getPassword());
    }

    @Description("Открыть настройки языка. Сменить язык интерфейса на английский." +
            "Проверить, что язык сменился.")
    @Test()
    public void checkChangeLanguage() {
        mainPage.openProfileMenu();
        mainPage.profileSettingBlock.languageSettingsButton.click();
        mainPage.changeInterfaceLanguage(mainPage.languageSettingsBlock.interfaceEnToggle);
        String header = mainPage.languageSettingsBlock.headerInterface.getText();
        mainPage.saveSettings();
        Assert.assertEquals(header, "Language settings",
                "Неверный заголовок интерфейса");
    }

    @AfterClass(alwaysRun = true)
    public void reset() {
        /*Сменить язык обратно на русский*/
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage.openProfileMenu();
        mainPage.profileSettingBlock.languageSettingsButton.click();
        mainPage.changeInterfaceLanguage(mainPage.languageSettingsBlock.interfaceRuToggle);
        mainPage.languageSettingsBlock.saveButton.click();
    }
}
