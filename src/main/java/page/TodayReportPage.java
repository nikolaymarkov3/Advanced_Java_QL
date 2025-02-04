package page;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Logger;

import java.util.List;
import java.util.Random;

@Getter
public class TodayReportPage extends CalendarPage{
	public TodayReportPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[contains(@class,'emoji') and @data-original-title]")
	private List<WebElement> moods;
	
	@FindBy(xpath = "//*[@id='modal-more-less-hours' and not(@aria-hidden)]//h5")
	private WebElement exampleModalLabel;
	
	@FindBy(xpath = "//*[@id='modal-more-less-hours' and not(@aria-hidden)]//*[contains(text(),'Отмена')]")
	private WebElement cancelButton;
	
	@Step("Выбор настроения в отчете за сегодня")
	public TodayReportPage clickMode(WebDriver driver) {
		Random random = new Random();
		int index = random.nextInt(getMoods().size());
		getMoods().get(index).click();
		Logger.logInfo("Выбор настроения");
		return this;
	}
	
	@Step("Значение модального окна")
	public String meaningModalLabel(WebDriver driver) {
		String message = getExampleModalLabel().getText();
		getCancelButton().click();
		return message;
	}
}
