package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.blocks.ProfileSettingBlock;

@Component
public class MainPage extends AbstractPage{

    @Autowired
    public ProfileSettingBlock profileSettingBlock;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn_navbar_user-dropdown")
    public WebElement userProfileButton;




}
