package runner;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Logger;
import java.time.Duration;

public class BaseTest {
	public static WebDriver driver;


//	public static RemoteWebDriver driver;

	@BeforeMethod
	public void initWebDriver() {
		try {
			System.setProperty("webdriver.chrome.logfile", "chromedriver.log");
			System.setProperty("webdriver.chrome.verboseLogging", "true");

			WebDriverManager.chromedriver().setup();
			Logger.logInfo("WebDriver настроен");

			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--window-size=500,500");
			options.addArguments("--headless"); // Уберите этот параметр для отладки
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");

			driver = new ChromeDriver(options);
			Logger.logInfo("Драйвер инициализирован успешно");

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//			driver.manage().window().setSize(new Dimension(200, 100));

			driver.get("https://tt-testing.quality-lab.ru");
			Logger.logInfo("Открыта страница: https://tt-testing.quality-lab.ru");

		} catch (Exception e) {
			System.out.println("Ошибка при инициализации драйвера: " + e.getMessage());
			e.printStackTrace(); // Вывод стека вызовов для дополнительной информации
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
