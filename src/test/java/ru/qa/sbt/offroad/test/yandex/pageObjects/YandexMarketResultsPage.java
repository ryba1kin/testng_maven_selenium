package ru.qa.sbt.offroad.test.yandex.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.qa.sbt.offroad.test.yandex.core.WebElementHelper;

import java.util.List;
import java.util.Set;

public class YandexMarketResultsPage extends YandexMarketPage {

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'n-filter-applied-results')]//h3[contains(@class,'n-snippet-card2__title')]")
    private List<WebElement> results;

    public YandexMarketResultsPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(getSearchArea()));
    }

    public YandexMarketResultsPage get(WebElement element) {
        WebElementHelper.click(element);
        return this;
    }
    public YandexMarketExtendedSearchPage extendedSearch() {
        Set<String> windowHandles = driver.getWindowHandles();
        WebElementHelper.click(getExtendedSearch());
        this.switchToActual(windowHandles);
        return new YandexMarketExtendedSearchPage(driver);
    }

    //getters

    public List<WebElement> getResults() {
        return results;
    }
}