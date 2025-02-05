package runner;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.ByteArrayInputStream;

public class TestListener extends BaseTest implements IInvokedMethodListener, ITestListener {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	WebDriver webDriver;
	
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		webDriver = driver;
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println("\n" + ANSI_RED_BACKGROUND + "Тест завершен не успешно " + ANSI_RESET + ANSI_RED
					                   + "--> [" + ANSI_RESET + method.getTestMethod().getMethodName() + ANSI_RED + "]" + ANSI_RESET);
			Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(((TakesScreenshot) webDriver)
					                                             .getScreenshotAs(OutputType.BYTES)), "png");
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println(ANSI_GREEN + "\nТест завершен успешно " + ANSI_RESET + ANSI_GREEN + "--> [" + ANSI_RESET + result.getName() + ANSI_GREEN + "]" + ANSI_RESET);
		}
	}
}
