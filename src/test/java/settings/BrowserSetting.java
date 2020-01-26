package settings;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;

import static ru.qa.sbt.offroad.test.yandex.variables.UriVariables.*;

public class BrowserSetting {
	
	public WebDriver BrowserSettings() {
		
		WebdriverSettings wds = new WebdriverSettings();
		WebDriver driver = wds.driverSettings();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(YANDEX_URL);
		return driver;
	}

}
