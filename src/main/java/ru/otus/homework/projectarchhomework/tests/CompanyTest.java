package ru.otus.homework.projectarchhomework.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        authorizationService.open(config.getUrl());
        mainPage.goToNavPage(mainPage.mainPageTabsBlock.companyButton);
    }

    @Test(description = "Найти компанию - Сбербанк. Проверить, что компания найдена")
    public void searchCompany(){
        companyPage.searchCompany(testCompany);
        Assert.assertEquals(companyPage.companiesList.size(),1,"Кампания не найдена");
    }
}
