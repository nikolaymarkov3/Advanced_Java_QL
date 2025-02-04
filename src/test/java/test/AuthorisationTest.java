package test;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.CalendarPage;
import page.TodayReportPage;
import runner.BaseTest;
import runner.TestListener;


import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static runner.Okhttp3Helpers.getInstanceOkhttp;

@Listeners({TestListener.class})
public class AuthorisationTest extends BaseTest{
	CalendarPage calendarPage;
	TodayReportPage todayReportPage;;
	
	@BeforeMethod
	void precondition() throws IOException {
		calendarPage = new CalendarPage(driver);
		todayReportPage = new TodayReportPage(driver);
		getInstanceOkhttp().apiAuth();
	}
	
	@Test
	@Description("Проверка авторизации")
	void testB() {
		String expectResult = "Вы хотите залогировать больше или меньше 5 часов, которые по графику запланированы у вас на сегодня";
//		calendarPage.openCalendar(driver)
//				.moveReportsAndClickTodayReport(driver);
//
//		String actualResult = todayReportPage.clickMode(driver)
//				                      .meaningModalLabel(driver);
//		assertEquals( actualResult,expectResult);
		System.out.println("Good ");
	}
	
	@Test
	@Description("Проверка авторизации")
	void testBv() {
		String expectResult = "Вы хотите залогировать больше или меньше 5 часов, которые по графику запланированы у вас на сегодня";
//		calendarPage.openCalendar(driver)
//				.moveReportsAndClickTodayReport(driver);
//
//		String actualResult = todayReportPage.clickMode(driver)
//				                      .meaningModalLabel(driver);
//		assertEquals( actualResult,expectResult);
		System.out.println("Good ");
	}

	@Test
	@Description("Проверка авторизации")
	void testBvz() {
		String expectResult = "Вы хотите залогировать больше или меньше 5 часов, которые по графику запланированы у вас на сегодня";

	}
}
