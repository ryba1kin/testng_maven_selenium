package ru.qa.sbt.offroad.test.yandex.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.qa.sbt.offroad.test.yandex.exceptions.AutotestException;
import ru.qa.sbt.offroad.test.yandex.pageObjects.base.BasePage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static ru.qa.sbt.offroad.test.yandex.core.WebElementHelper.*;

public class YandexMarketExtendedSearchPage extends BasePage {

    private static final By MANUFACTURERS = By.xpath("//div[@data-filter-id='7893318']");

    private static final By ELEMENTS = By.xpath(".//span[contains(@class,'checkbox') and contains(@class,'i-bem')]");

    private static final String ELEMENT_EXTENDED = ".//label[text() = '%s']";

    private static final By BUTTON_EXPAND = By.xpath(".//button[contains(@class,'button_size_xs')]");

    private static final By INPUT = By.xpath(".//input[@class='input__control']");

    @FindBy(how = How.CLASS_NAME, using = "title")
    private WebElement title;

    @FindBy(how = How.CLASS_NAME, using = "button_action_show-filtered")
    private WebElement applyFilter;

    @FindBy(how = How.ID, using = "glf-pricefrom-var")
    private WebElement priceFrom;


    @FindBy(how = How.ID, using = "glf-priceto-var")
    private WebElement priceTo;


    public YandexMarketExtendedSearchPage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOf(title));
    }

    public YandexMarketExtendedSearchPage get(WebElement element) {
        click(element);
        return this;
    }

    public YandexMarketExtendedSearchPage checkManufacturers(String... arrayManufacturers) throws AutotestException {

        List<WebElement> manufacturers;
        wait.until(visibilityOfAllElements(
                manufacturers = driver.findElement(MANUFACTURERS).findElements(ELEMENTS)
        ));
        List<String> labels = manufacturers
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Set<String> uniqueManufacturers = new HashSet<String>() {{
            addAll(Arrays.asList(arrayManufacturers));
        }};

        if (labels.containsAll(uniqueManufacturers)) {
            for (String manufacturer : uniqueManufacturers) {
                manufacturers.get(labels.indexOf(manufacturer)).click();
            }
        } else {
            click(BUTTON_EXPAND);

            for (String manufacturer : uniqueManufacturers) {
                checkExtendElement(MANUFACTURERS, manufacturer);
            }
        }
        return this;
    }

    public YandexMarketResultsPage apply() {
        Set<String> windowHandles = driver.getWindowHandles();
        wait.until(visibilityOf(applyFilter));
        click(applyFilter);
        this.switchToActual(windowHandles);
        return new YandexMarketResultsPage(driver);
    }

    public YandexMarketExtendedSearchPage checkPrice(WebElement element, long price) {
        wait.until(elementToBeClickable(element));
        sendKeys(element, String.valueOf(price));
        return this;
    }

    private void checkExtendElement(By filterLocator, String elementText) throws AutotestException {
        try {
            wait.until(presenceOfNestedElementLocatedBy(filterLocator, INPUT));
            sendKeys(driver.findElement(filterLocator).findElement(INPUT), elementText);
            wait.until(presenceOfNestedElementsLocatedBy(filterLocator, By.xpath(format(ELEMENT_EXTENDED, elementText))));
            click(driver.findElement(filterLocator).findElement(By.xpath(format(ELEMENT_EXTENDED, elementText))));
        } catch (Exception e) {
            throw new AutotestException(format("can not find [%s]", elementText), e);
        }
    }
    //getters

    public WebElement getPriceFrom() {
        return priceFrom;
    }

    public WebElement getPriceTo() {
        return priceTo;
    }
}