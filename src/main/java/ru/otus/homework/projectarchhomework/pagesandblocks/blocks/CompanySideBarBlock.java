package ru.otus.homework.projectarchhomework.pagesandblocks.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;
import ru.otus.homework.projectarchhomework.pagesandblocks.pages.AbstractPage;

@Component
public class CompanySideBarBlock extends AbstractPage {
    public CompanySideBarBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public By countTopics = By.cssSelector(".stacked-menu__item-counter_black");

    @FindBy(xpath = ".//a[span[contains(text(),'Все')]]")
    public WebElement allTopicsButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Веб-разработка')]]")
    public WebElement webDevelopmentTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Программное обеспечение')]]")
    public WebElement softwareTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Аппаратное обеспечение')]]")
    public WebElement hardwareTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Дизайн и юзабилити')]]")
    public WebElement designTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Реклама и маркетинг')]]")
    public WebElement marketingTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'СМИ')]]")
    public WebElement newsTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Консалтинг и поддержка')]]")
    public WebElement consultingTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Рекрутинг и HR')]]")
    public WebElement hrTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Электронная коммерция')]]")
    public WebElement commerceTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Некоммерческие организации')]]")
    public WebElement NoCommerceOrganisationTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Оптимизация')]]")
    public WebElement optimisationTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Производство мультимедиа-контента')]]")
    public WebElement mediaTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Связь и телекоммуникации')]]")
    public WebElement communicationTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Домены и хостинг')]]")
    public WebElement hostingTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Поисковые технологии')]]")
    public WebElement searchTopicButton;

    @FindBy(xpath = "..//a[span[contains(text(),'Мобильные технологии')]]")
    public WebElement mobileTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Веб-сервисы')]]")
    public WebElement webServicesTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Игры и развлечения')]]")
    public WebElement gamesTopicButton;

    @FindBy(xpath = ".//a[span[contains(text(),'Информационная безопасность')]]")
    public WebElement infoSecurityTopicButton;
}
