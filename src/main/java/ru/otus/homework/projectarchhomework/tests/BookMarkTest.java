package ru.otus.homework.projectarchhomework.tests;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import ru.otus.homework.projectarchhomework.ProjectArchHomeworkApplication;
import ru.otus.homework.projectarchhomework.config.BaseWebDrivingTest;
import ru.otus.homework.projectarchhomework.config.Config;

@SpringBootTest(classes =  ProjectArchHomeworkApplication.class)
@ContextConfiguration(classes = Config.class)
@Test(groups = "smoke")
public class BookMarkTest extends BaseWebDrivingTest {
    //Добавить несколько статей в закладки, и проверить, что статьи отобразились.
    /*
    *1. Найти несколько статей через поиск
    *2.добавить в закладки
    *3.Перейти на Профиль-Закладки
    *4.Проверить, что закладки отобразились
    *5.Удалить одну статью из закладок
    *6.Проверить, что осталась только одна статья в закладке
    * */
}
