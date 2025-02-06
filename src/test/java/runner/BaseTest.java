package runner;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Logger;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Objects;


public class BaseTest {
	public static WebDriver driver;


    @BeforeMethod
	public void initWebDriver() {
//		System.setProperty("webdriver.chrome.driver", "N:/DESCTOP/Chromedriver/chromedriver-win64");
	try {


		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-size=500,500");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

//		System.setProperty("webdriver.chrome.driver", "N:/DESCTOP/Chromedriver/chromedriver-win64");
		if (driver == null) {
//			System.setProperty("webdriver.chrome.driver", "N:/DESCTOP/Chromedriver/chromedriver-win64/chromedriver");
			driver = new ChromeDriver(options);
			Logger.logInfo("Инициализация драйвера");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}

//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().setSize(new Dimension(200, 100));
		driver.get("https://tt-testing.quality-lab.ru");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (Objects.equals(js.executeScript("return document.readyState"), "complete")) {//проверяем загрузилась ли страница
			driver.manage().window().maximize();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
