package ru.otus.homework.projectarchhomework.pagesandblocks.blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.AbstractPage;

@Component
public class LanguageSettingsBlock extends AbstractPage {
    public LanguageSettingsBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".popup")
    public WebElement popupMenu;

    @FindBy(css = ".popup__head_lang-settings>span")
    public WebElement headerInterface;

    @FindBy(xpath = ".//span[@class='radio radio_custom ']/label[contains(text(),'Русский')]")
    public WebElement interfaceRuToggle;

    @FindBy(xpath = ".//span[@class='radio radio_custom ']/label[contains(text(),'English')]")
    public WebElement interfaceEnToggle;

    @FindBy(css = ".js-popup_save_btn")
    public WebElement saveButton;
}
