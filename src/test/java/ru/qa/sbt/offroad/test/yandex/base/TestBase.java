package ru.qa.sbt.offroad.test.yandex.base;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ru.qa.sbt.offroad.test.yandex.core.DriverBase;
import ru.qa.sbt.offroad.test.yandex.listeners.ScreenshotListener;

import static ru.qa.sbt.offroad.test.yandex.constants.Constants.APP_URL;
import static ru.qa.sbt.offroad.test.yandex.core.DriverBase.getDriver;

@Listeners(ScreenshotListener.class)
public class TestBase {

    @BeforeMethod(alwaysRun = true)
    public void instantiateDriverObject() {
        DriverBase.instantiateDriverObject();
        String sessionId = ((RemoteWebDriver) getDriver()).getSessionId().toString();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(APP_URL);

    }

    @AfterMethod(alwaysRun = true)
    public void closeDriverObjects() {
        DriverBase.closeDriverObjects();
    }
}