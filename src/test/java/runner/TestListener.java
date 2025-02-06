package runner;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.ByteArrayInputStream;

public class TestListener extends BaseTest implements IInvokedMethodListener, ITestListener {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	
//	@Override
//	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
//		if (testResult.getStatus() == ITestResult.FAILURE) {
//			System.out.println("\n" + ANSI_RED_BACKGROUND + "Тест завершен не успешно " + ANSI_RESET + ANSI_RED
//					                   + "--> [" + ANSI_RESET + method.getTestMethod().getMethodName() + ANSI_RED + "]" + ANSI_RESET);
//			Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(((TakesScreenshot) driver)
//					                                             .getScreenshotAs(OutputType.BYTES)), "png");
//		}
//	}
	
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		if (result.getStatus() == ITestResult.SUCCESS) {
//			System.out.println(ANSI_GREEN + "\nТест завершен успешно " + ANSI_RESET + ANSI_GREEN + "--> [" + ANSI_RESET + result.getName() + ANSI_GREEN + "]" + ANSI_RESET);
//		}
//	}
}
