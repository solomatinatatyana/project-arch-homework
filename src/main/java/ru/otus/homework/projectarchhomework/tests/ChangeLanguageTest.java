package ru.otus.homework.projectarchhomework.tests;

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

@SpringBootTest(classes =  ProjectArchHomeworkApplication.class)
@ContextConfiguration(classes = Config.class)
@Test(groups = "smoke")
public class ChangeLanguageTest extends BaseWebDrivingTest {
    private Logger log = LogManager.getLogger(ChangeLanguageTest.class);
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private MainPage mainPage;

    @BeforeClass(alwaysRun = true)
    public void init(){
        authorizationService.doLogin(config.getUrl(), config.getUsername(),config.getPassword());
    }

    @Test(description = "Открыть настройки языка. Сменить язык интерфейса на английский." +
            "Проверить, что язык сменился.")
    public void checkChangeLanguage(){
        mainPage.openProfileMenu();
        mainPage.profileSettingBlock.languageSettingsButton.click();
        mainPage.changeInterfaceLanguage(mainPage.languageSettingsBlock.interfaceEnToggle);
        mainPage.languageSettingsBlock.saveButton.click();
        Assert.assertEquals(mainPage.languageSettingsBlock.headerInterface.getText(),"Language settings",
                "Неверный заголовок интерфейса");
    }

    @AfterClass(alwaysRun = true)
    public void reset(){
        /*Сменить язык обратно на русский*/
    }
}
