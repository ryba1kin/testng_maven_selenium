package ru.qa.sbt.offroad.test.yandex.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.qa.sbt.offroad.test.yandex.core.WebElementHelper;
import ru.qa.sbt.offroad.test.yandex.pageObjects.base.BasePage;

import java.util.Set;

public class YandexStartPage extends BasePage {

    @FindBy(how = How.ID, using = "text")
    private WebElement inputField;

    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement searchButton;

    @FindBy(how = How.LINK_TEXT, using = "Маркет")
    private WebElement marketLink;

    public YandexStartPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(inputField));
    }

    public YandexMarketPage goToMarket() {
        Set<String> windowHandles = driver.getWindowHandles();
        WebElementHelper.click(marketLink);
        this.switchToActual(windowHandles);
        return new YandexMarketPage(driver);
    }


    public YandexStartPage search(String text) {
        WebElementHelper.sendKeys(inputField, text);
        WebElementHelper.click(searchButton);
        return this;
    }
}