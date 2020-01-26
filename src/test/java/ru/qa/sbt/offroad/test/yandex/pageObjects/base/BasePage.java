package ru.qa.sbt.offroad.test.yandex.pageObjects.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static ru.qa.sbt.offroad.test.yandex.constants.Constants.WAIT_EXPLICIT_SEC;
import static ru.qa.sbt.offroad.test.yandex.core.FunctionalHelper.not;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_EXPLICIT_SEC), this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, WAIT_EXPLICIT_SEC);
    }



    public void switchToActual(Set<String> beforeSwitch) {
        driver.switchTo().window(
                driver.getWindowHandles().stream()
                        .filter(not(beforeSwitch::contains))
                        .findFirst()
                        .orElse(driver.getWindowHandle())
        );
    }
}
