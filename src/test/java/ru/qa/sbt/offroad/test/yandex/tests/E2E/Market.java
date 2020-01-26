package ru.qa.sbt.offroad.test.yandex.tests.E2E;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import ru.qa.sbt.offroad.test.yandex.base.TestBase;
import ru.qa.sbt.offroad.test.yandex.pageObjects.YandexMarketExtendedSearchPage;
import ru.qa.sbt.offroad.test.yandex.pageObjects.YandexMarketPage;
import ru.qa.sbt.offroad.test.yandex.pageObjects.YandexMarketResultsPage;
import ru.qa.sbt.offroad.test.yandex.pageObjects.YandexStartPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static ru.qa.sbt.offroad.test.yandex.core.DriverBase.getDriver;

@Epic("Yandex")
@Feature("Yandex Market")
public class Market extends TestBase {

    @Test(groups = {"all"})
    public void computersFilter() throws Exception {
        //init
        YandexStartPage yandexStartPage = new YandexStartPage(getDriver());
        //navigate
        YandexMarketPage market = yandexStartPage.goToMarket();
        //navigate on page
        market.get(market.getComputerTechnics())
                .get(market.getNotebooks());
        //set filters
        YandexMarketExtendedSearchPage searchCondition = market.extendedSearch();
        searchCondition
                .checkPrice(searchCondition.getPriceTo(), 30_000)
                .checkManufacturers("HP", "Lenovo");

        YandexMarketResultsPage resultsPage = searchCondition.apply();
        //assertions

        assertThat(resultsPage.getResults().size(), is(48));

        String firstResult = resultsPage.getResults()
                .get(0).getText();
        resultsPage = resultsPage.search(firstResult);
        assertThat(resultsPage.getResults().get(0).getText(), containsString(firstResult));
    }

    @Test(groups = {"all"})
    public void notepadsFilter() throws Exception {
        //init
        YandexStartPage yandexStartPage = new YandexStartPage(getDriver());
        //navigate
        YandexMarketPage market = yandexStartPage.goToMarket();
        //navigate on page
        market.get(market.getComputerTechnics())
                .get(market.getNotepads());
        //set filters
        YandexMarketExtendedSearchPage searchCondition = market.extendedSearch();
        searchCondition
                .checkPrice(searchCondition.getPriceFrom(), 20_000)
                .checkPrice(searchCondition.getPriceTo(), 25_000)
                .checkManufacturers("Acer", "DELL");

        YandexMarketResultsPage resultsPage = searchCondition.apply();

        //assertions
        assertThat(resultsPage.getResults().size(), is(48));

        String firstResult = resultsPage.getResults()
                .get(0).getText();
        resultsPage = resultsPage.search(firstResult);
        assertThat(resultsPage.getResults().get(0).getText(), containsString(firstResult));
    }

    @Test(groups = {"all"})
    public void notepadsNotFailedFilter() throws Exception {
        //init
        YandexStartPage yandexStartPage = new YandexStartPage(getDriver());
        //navigate
        YandexMarketPage market = yandexStartPage.goToMarket();
        //navigate on page
        market.get(market.getComputerTechnics())
                .get(market.getNotepads());
        //set filters
        YandexMarketExtendedSearchPage searchCondition = market.extendedSearch();
        searchCondition
                .checkPrice(searchCondition.getPriceFrom(), 20_000)
                .checkPrice(searchCondition.getPriceTo(), 25_000)
                .checkManufacturers("Apple", "HP", "Lenovo", "Samsung");

        YandexMarketResultsPage resultsPage = searchCondition.apply();

        //assertions
        assertThat(resultsPage.getResults().size(), is(25));

        String firstResult = resultsPage.getResults()
                .get(0).getText();
        resultsPage = resultsPage.search(firstResult);
        assertThat(resultsPage.getResults().get(0).getText(), containsString(firstResult));
    }
}
