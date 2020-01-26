package ru.qa.sbt.offroad.test.yandex.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class YandexStartPage {

    WebDriver driver;

    public YandexStartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
