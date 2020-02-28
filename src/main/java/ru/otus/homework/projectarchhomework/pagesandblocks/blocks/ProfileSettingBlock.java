package ru.otus.homework.projectarchhomework.pagesandblocks.blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.AbstractPage;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Component
public class ProfileSettingBlock extends AbstractPage {

    public ProfileSettingBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".dropdown-container.dropdown-container_white")
    public WebElement profileContainer;

    @FindBy(css = ".dropdown__user-info.user-info")
    public WebElement profileInfo;

    @FindBy(css = "ul[class = 'n-dropdown-menu n-dropdown-menu_profile']>li:nth-child(3)>a")
    public WebElement bookmarkButton;

    @FindBy(css = "ul[class = 'n-dropdown-menu n-dropdown-menu_profile']>li:nth-child(5)>a")
    public WebElement languageSettingsButton;

    @FindBy(css = "ul[class = 'n-dropdown-menu n-dropdown-menu_profile']>li:nth-child(7)>a")
    public WebElement logoutButton;

}
