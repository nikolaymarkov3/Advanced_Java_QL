package runner;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.okhttp3.ReportPortalOkHttp3LoggingInterceptor;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.OkHttpClient;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class BaseTest {
	public static WebDriver driver;

//	public static RemoteWebDriver driver;

	@BeforeMethod
	public void initWebDriver() {
		WebDriverManager.chromedriver().setup();

		Logger.logInfo("Инициализация драйвера");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-size=500,500");
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

		if (driver == null) {
			try {
				driver = new ChromeDriver(options);
				Logger.logInfo("Драйвер инициализирован успешно");
			} catch (Exception e) {
				System.out.printf("Ошибка при инициализации драйвера: " + e.getMessage());
				throw e; // Пробрасываем исключение дальше
			}
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().setSize(new Dimension(200, 100));

		try {
			driver.get("https://tt-testing.quality-lab.ru");
			Logger.logInfo("Открыта страница: https://tt-testing.quality-lab.ru");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			if (Objects.equals(js.executeScript("return document.readyState"), "complete")) {
				driver.manage().window().maximize();
				Logger.logInfo("Страница загружена и окно развернуто");
			}
		} catch (Exception e) {
			System.out.println("Ошибка при загрузке страницы: " + e.getMessage());
			throw e; // Пробрасываем исключение дальше
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
