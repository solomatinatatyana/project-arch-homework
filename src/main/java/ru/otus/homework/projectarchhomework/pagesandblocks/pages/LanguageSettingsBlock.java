package ru.otus.homework.projectarchhomework.pagesandblocks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class LanguageSettingsBlock extends AbstractPage{
    public LanguageSettingsBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".popup")
    public WebElement popupMenu;

    @FindBy(css = ".popup__head_lang-settings>span")
    public WebElement headerInterface;

    @FindBy(xpath = ".//label[@class='radio radio_custom ']/span[contains(text(),'Русский')]")
    public WebElement interfaceRuToggle;

    @FindBy(xpath = ".//label[@class='radio radio_custom ']/span[contains(text(),'English')]")
    public WebElement interfaceEnToggle;

    @FindBy(css = ".js-popup_save_btn")
    public WebElement saveButton;
}
