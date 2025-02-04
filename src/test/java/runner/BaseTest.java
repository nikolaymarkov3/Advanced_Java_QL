package runner;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.okhttp3.ReportPortalOkHttp3LoggingInterceptor;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
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
	public void initWebDriver() throws MalformedURLException {
//		Logger.logInfo("Инициализация драйвера");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-size=500,500");
//		ChromeOptions options = new ChromeOptions();
		
//		options.setCapability("selenoid:options", new HashMap<String, Object>() {{
//			/* How to add test badge */
//			put("name", "Test badge...");
//
//			/* How to set session timeout */
//			put("sessionTimeout", "15m");
//
//			/* How to set timezone */
//			put("env", new ArrayList<String>() {{
//				add("TZ=UTC");
//			}});
//
//			/* How to add "trash" button */
//			put("labels", new HashMap<String, Object>() {{
//				put("manual", "true");
//			}});
//
//			/* How to enable video recording */
//			put("enableVideo", true);
//		}});
//		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		
		// Устанавливаем размер окна
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ReportPortalOkHttp3LoggingInterceptor(LogLevel.INFO))
                .build();
		if (driver == null) {
//			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
			driver = new ChromeDriver(options);
			Logger.logInfo("Инициализация драйвера");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().setSize(new Dimension(200, 100));
		driver.get("https://tt-testing.quality-lab.ru");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if(Objects.equals(js.executeScript("return document.readyState"), "complete")) {//проверяем загрузилась ли страница
			driver.manage().window().maximize();
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
