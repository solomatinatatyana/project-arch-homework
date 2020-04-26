package ru.otus.homework.projectarchhomework.services.auth;

import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.LoginPage;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.MainPage;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    public LoginPage loginPage;
    public MainPage mainPage;

    @Autowired
    public AuthorizationServiceImpl(LoginPage loginPage, MainPage mainPage) {
        this.loginPage = loginPage;
        this.mainPage = mainPage;
    }

    public void login(String url, String login, String password) {
        loginPage.driver.get(url);
        loginUI(login, password);
    }

    private void loginUI(String login, String password) {
        loginPage.signButton.click();
        WebDriverWait wait = (new WebDriverWait(loginPage.driver, 50L));
        wait.until(ExpectedConditions.visibilityOfAllElements(
                loginPage.username,
                loginPage.pass,
                loginPage.submit));
        loginPage.username.clear();
        loginPage.username.sendKeys(login);
        loginPage.pass.clear();
        loginPage.pass.sendKeys(password);
        loginPage.submit.click();
    }

    @Override
    @Step("Логин")
    public void doLogin(String url, String login, String password) {
        this.login(url, login, password);
    }

    @Override
    @Step("Логин")
    public void doLogin(String login, String password) {
        this.loginUI(login, password);
    }

    @Override
    @Step("Разлогин")
    public void logout() {
        WebDriverWait wait = (new WebDriverWait(mainPage.driver, 50L));
        wait.until(ExpectedConditions.visibilityOfAllElements(mainPage.userProfileButton));
        mainPage.userProfileButton.click();
        mainPage.profileSettingBlock.logoutButton.click();
        WebDriverWait wait2 = new WebDriverWait(mainPage.driver, 100L);
        wait2.until(ExpectedConditions.visibilityOfAllElements(loginPage.signButton));
    }

    @Override
    @Step("Переход на сайт {url}")
    public void open(String url) {
        loginPage.driver.get(url);
    }

    @Override
    @Step("Переход на страницу авторизации сайта -  {url}")
    public void goToLoginPage(String url) {
        this.open(url);
        loginPage.signButton.click();
    }
}
