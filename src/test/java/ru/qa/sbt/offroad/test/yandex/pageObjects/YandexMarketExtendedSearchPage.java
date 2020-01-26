package ru.qa.sbt.offroad.test.yandex.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.qa.sbt.offroad.test.yandex.core.WebElementHelper;
import ru.qa.sbt.offroad.test.yandex.exceptions.AutotestException;
import ru.qa.sbt.offroad.test.yandex.pageObjects.base.BasePage;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.qa.sbt.offroad.test.yandex.core.FunctionalHelper.not;

public class YandexMarketExtendedSearchPage extends BasePage {

    @FindBy(how = How.CLASS_NAME, using = "title")
    private WebElement title;

    @FindBy(how = How.CLASS_NAME, using = "button_action_show-filtered")
    private WebElement applyFilter;

    @FindBy(how = How.ID, using = "glf-pricefrom-var")
    private WebElement priceFrom;


    @FindBy(how = How.ID, using = "glf-priceto-var")
    private WebElement priceTo;

    private static final String MANUFACTURERS = "//div[@data-filter-id='7893318']";

    @FindBy(how = How.XPATH, using = MANUFACTURERS + "//span[contains(@class,'checkbox') and contains(@class,'i-bem')]")
    private List<WebElement> manufacturersElements;

    @FindBy(how = How.XPATH, using = MANUFACTURERS + "//button[contains(@class,'button_size_xs')]")
    private WebElement manufacturersButtonExpand;

    @FindBy(how = How.XPATH, using = MANUFACTURERS + "//input[@class='input__control']")
    private WebElement manufacturersInput;

    public YandexMarketExtendedSearchPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(title));
    }

    public YandexMarketExtendedSearchPage get(WebElement element) {
        WebElementHelper.click(element);
        return this;
    }

    public YandexMarketExtendedSearchPage checkManufacturers(String... arrayManufacturers) throws AutotestException {

        wait.until(ExpectedConditions.visibilityOfAllElements(getManufacturersElements()));
        List<String> labels = getTextOf(manufacturersElements);
        List<String> listManufacturers = Arrays.asList(arrayManufacturers);

        if (labels.containsAll(listManufacturers)) {
            for (String manufacturer : listManufacturers) {
                manufacturersElements.get(labels.indexOf(manufacturer)).click();
            }
        } else {
            wait.until(ExpectedConditions.visibilityOf(manufacturersButtonExpand));
            WebElementHelper.click(manufacturersButtonExpand);
            labels = getTextOf(manufacturersElements);
            if (labels.containsAll(listManufacturers)) {
                for (String manufacturer : listManufacturers) {
                    WebElementHelper.sendKeys(manufacturersInput, manufacturer);
                    WebElement element = manufacturersElements.get(labels.indexOf(manufacturer));
                    WebElementHelper.click(element);
                }
            } else {
                List<String> finalLabels = labels;
                System.out.println(labels.size());
                throw new AutotestException(
                        String.format("can not find [%s]",
                                listManufacturers.stream()
                                        .filter(not(finalLabels::contains))
                                        .collect(Collectors.joining(","))
                        ));
            }
        }
        return this;
    }

    private List<String> getTextOf(List<WebElement> elements) {
        return elements.stream()
                .map(element ->
                        (String) ((JavascriptExecutor) driver)
                                .executeScript("return arguments[0].innerText", element)
                )
                .collect(Collectors.toList());
    }

    public YandexMarketResultsPage apply() {
        Set<String> windowHandles = driver.getWindowHandles();
        wait.until(ExpectedConditions.visibilityOf(applyFilter));
        WebElementHelper.click(applyFilter);
        this.switchToActual(windowHandles);
        return new YandexMarketResultsPage(driver);
    }

    public YandexMarketExtendedSearchPage checkPrice(WebElement element, long price) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        WebElementHelper.sendKeys(element, String.valueOf(price));
        return this;
    }
    //getters

    public WebElement getPriceFrom() {
        return priceFrom;
    }

    public WebElement getPriceTo() {
        return priceTo;
    }

    public List<WebElement> getManufacturersElements() {
        return manufacturersElements;
    }

    public WebElement getManufacturersButtonExpand() {
        return manufacturersButtonExpand;
    }
}