# testng_maven_selenium

**Prerequisite**
1. Java environment 8
2. Maven

**Note: Configure web-driver path.**
	

- Configure webdriver.
    - I used ChromeDriver but you can choose other webdriver eg. Mozilla GeckoDriver 
    - So first download ChromeDriver from [http://chromedriver.chromium.org/downloads](http://chromedriver.chromium.org/downloads).
    - Copy your downloaded **chromedriver.exe** file path.
    - Go to `<project_folder>\src\test\java\testcases\mytheresa\WebdriverSettings.java` file.
    - Paste the copied file path in `System.setProperty("webdriver.chrome.driver","Paste path here")`.


	
Project is ready to run. Execute following commands and enjoy the automatic web-driver test.
- Go to project path in terminal. For example: C:\Users\User-Name\eclipse-workspace\E2EUserJourney>
- Run `mvn clean`
- Run `mvn compile`
- Run `mvn test`

You can find the test result in `<project_folder>\target\surefire-reports\index.html`.