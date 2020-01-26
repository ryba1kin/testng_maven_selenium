package ru.qa.sbt.offroad.test.yandex.pageObjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.qa.sbt.offroad.test.yandex.core.WebElementHelper;
import ru.qa.sbt.offroad.test.yandex.pageObjects.base.BasePage;

import java.util.Set;

public class YandexMarketPage extends BasePage {

    @FindBy(how = How.CLASS_NAME, using = "n-region-notification_layout_init")
    private WebElement popup;

    @FindBy(how = How.CLASS_NAME, using = "n-region-notification__ok")
    private WebElement popupYesButton;

    @FindBy(how = How.ID, using = "header-search")
    private WebElement searchArea;

    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement searchButton;

    @FindBy(how = How.LINK_TEXT, using = "Компьютерная техника")
    private WebElement computerTechnics;

    @FindBy(how = How.LINK_TEXT, using = "Ноутбуки")
    private WebElement notebooks;

    @FindBy(how = How.LINK_TEXT, using = "Планшеты")
    private WebElement notepads;

    @FindBy(how = How.LINK_TEXT, using = "Все фильтры")
    private WebElement extendedSearch;

    public YandexMarketPage(WebDriver driver) {
        super(driver);
        this.closeCityIfPresent();
        wait.until(ExpectedConditions.visibilityOf(searchArea));
    }

    private void closeCityIfPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(popup));
            WebElementHelper.click(popupYesButton);
        } catch (TimeoutException ex){
            return;
        }
    }

    public YandexMarketPage get(WebElement element) {
        WebElementHelper.click(element);
        return this;
    }

    //getters


    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getSearchArea() {
        return searchArea;
    }

    public WebElement getComputerTechnics() {
        return computerTechnics;
    }

    public WebElement getNotebooks() {
        return notebooks;
    }

    public WebElement getNotepads() {
        return notepads;
    }

    public WebElement getExtendedSearch() {
        return extendedSearch;
    }

    public YandexMarketExtendedSearchPage extendedSearch() {
        Set<String> windowHandles = driver.getWindowHandles();
        WebElementHelper.click(extendedSearch);
        this.switchToActual(windowHandles);
        return new YandexMarketExtendedSearchPage(driver);
    }

    public YandexMarketResultsPage search(String query){
        wait.until(ExpectedConditions.visibilityOf(getSearchArea()));
        Set<String> windowHandles = driver.getWindowHandles();
        WebElementHelper.sendKeys(getSearchArea(), query);
        WebElementHelper.click(getSearchButton());
        this.switchToActual(windowHandles);
        return new YandexMarketResultsPage(driver);
    }
}