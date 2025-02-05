package page;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.Logger;

import java.util.Objects;

@Getter
public class CalendarPage extends BasePage{
    public CalendarPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@href='/calendar/']")
    private WebElement openCalendar;
    
    @FindBy(xpath = "//*[contains(text(), 'Изменить')]")
    private WebElement buttonChange;
    
    @FindBy(xpath = "//*[contains(text(),'Отчеты')]/preceding-sibling::*")
    private WebElement reports;
    
    @FindBy(xpath = "//*[contains(text(),'Отчет за сегодня')]")
    private WebElement TodayReport;
    
    public CalendarPage openCalendar(WebDriver driver) {
        driver.get("https://tt-testing.quality-lab.ru/calendar/");
        return this;
    }
    
    public CalendarPage openReportsForToday(WebDriver driver) {
        driver.get("https://tt-testing.quality-lab.ru/report/group/edit");
        return this;
    }
    
    @Step("Навести курсор на кнопку отчеты")
    public CalendarPage moveReportsAndClickTodayReport(WebDriver driver) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        if(Objects.equals(js.executeScript("return document.readyState"), "complete")) {//проверяем загрузилась ли страница
            new Actions(driver)
                    .moveToElement(getReports())
                    .click(getTodayReport())
                    .build()
                    .perform();
        Logger.logInfo("Навести курсор на кнопку отчеты");
        
        
        return this;
    }
}
