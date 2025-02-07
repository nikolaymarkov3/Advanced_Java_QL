package runner;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;


public class BaseTest {
	public static WebDriver driver;
//	public static RemoteWebDriver driver;


    @BeforeMethod
	public void initWebDriver() throws MalformedURLException {



		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
//		options.addArguments("--window-size=500,500");
		options.addArguments("--log-level=3");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

//		System.setProperty("webdriver.chrome.driver", "N:/DESCTOP/Chromedriver/chromedriver-win64");
		if (driver == null) {
//			System.setProperty("webdriver.chrome.driver", "N:/DESCTOP/Chromedriver/chromedriver-win64/chromedriver");
			driver = new ChromeDriver(options);
//			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//			driver = new RemoteWebDriver(new URL("http://selenium-hub1:4444/wd/hub"), options);
//			driver = new RemoteWebDriver(new URL("http://selenoid:4444"), options);
			Logger.logInfo("Инициализация драйвера");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			driver.manage().window().maximize();
		}

//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		driver.manage().window().setSize(new Dimension(200, 100));
		driver.get("https://tt-testing.quality-lab.ru");

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		if (Objects.equals(js.executeScript("return document.readyState"), "complete")) {//проверяем загрузилась ли страница
//			driver.manage().window().maximize();
//		}

	}
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
