# testng_maven_selenium

**Prerequisite**
1. Java environment 8
2. Maven

**Note: Configure web-driver path.**
	

- Configure webdriver.
    - I used ChromeDriver but you can choose other webdriver eg. Mozilla GeckoDriver 
    - So first download ChromeDriver from [http://chromedriver.chromium.org/downloads](http://chromedriver.chromium.org/downloads).
    - Copy your downloaded **chromedriver.exe** file path.
    - Go to `<project_folder>\src\test\java\ru\qa\sbt\offroad\test\yandex\core\DriverType.java` file.
    - Paste the copied file path in `System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_79/chromedriver.exe");`.


	
