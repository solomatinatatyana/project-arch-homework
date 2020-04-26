package ru.otus.homework.projectarchhomework.services.auth;

public interface AuthorizationService {
    void doLogin(String url, String login, String password);

    void doLogin(String login, String password);

    void logout();

    void open(String url);

    void goToLoginPage(String url);
}
