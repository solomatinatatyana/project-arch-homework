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
public class MainPageTest extends BaseWebDrivingTest {

    @Test()
    public void test(){
        System.out.println("MainPageTest");
    }
}
