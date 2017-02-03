package qa.events;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class AdminStagingNominations extends BasePage {
	WaitTool wait;
	InterruptTool interrupt;

	public AdminStagingNominations(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	public void toPage() {
		// TODO Auto-generated method stub
		driver.navigate().to("http://admin.staging.bizjournals.com/event/calendar");
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement createNominationButton() {
		return driver.findElement(By.linkText("CREATE NEW NOMINATION"));
	}

	private WebElement selectEventDropdown() {
		return driver.findElement(By.id("event_id"));
	}

	private WebElement selectTypeDropdown() {
		return driver.findElement(By.id("nomination_type"));
	}

	private WebElement submitNominationButton() {
		return driver.findElement(By.id("saveButton"));
	}

	private WebElement nominationTitleField() {
		return driver.findElement(By.id("title"));
	}

	private WebElement nominationStartDateField() {
		return driver.findElement(By.id("start_date"));
	}

	private WebElement nominationEndDateField() {
		return driver.findElement(By.id("end_date"));
	}

	private WebElement datePickerCalendar() {
		return driver.findElement(By.className("ui-datepicker-calendar"));
	}

	private WebElement saveNominationButton() {
		return driver.findElement(By.name("save-button"));
	}

	@SuppressWarnings("unused")
	private WebElement viewNominationButton() {
		return driver.findElement(By.name("preview-button"));
	}

	@SuppressWarnings("unused")
	private WebElement nominationHeadline() {
		return driver.findElement(By.className("primary")).findElement(By.className("article__headline"));
	}

	private List<WebElement> nominationsTable() {
		return driver.findElement(By.id("tabs")).findElements(By.tagName("tr"));
	}

	// --------------------------Helpers-------------------------------------//

	public void selectEvent(String event) {
		selectEventDropdown().click();
		for (int i = 0; i < selectEventDropdown().findElements(By.tagName("option")).size(); i++) {
			if (selectEventDropdown().findElements(By.tagName("option")).get(i).getText().contains(event)) {
				selectEventDropdown().findElements(By.tagName("option")).get(i).click();
			}
		}
	}

	public void selectEvent(int i) {
		selectEventDropdown().click();
		Assert.assertTrue("No events for nomination in the current market!",
				selectEventDropdown().findElements(By.tagName("option")).size() > 1);
		selectEventDropdown().findElements(By.tagName("option")).get(i).click();
	}

	public void selectType(String type) {
		selectTypeDropdown().click();
		for (int i = 0; i < selectTypeDropdown().findElements(By.tagName("option")).size(); i++) {
			if (selectTypeDropdown().findElements(By.tagName("option")).get(i).getText().contains(type)) {
				selectTypeDropdown().findElements(By.tagName("option")).get(i).click();
			}
		}
	}

	public void selectType(int i) {
		selectTypeDropdown().click();
		selectTypeDropdown().findElements(By.tagName("option")).get(i).click();
	}

	public void setStartDate() {
		Calendar c = Calendar.getInstance();		
		Date date = new Date();
		c.setTime(date);
		date = c.getTime();
		c.add(Calendar.DATE, -2);
		nominationStartDateField().click();
		Assert.assertTrue("Date picker did not appear!", datePickerCalendar().isDisplayed());
		datePickerCalendar().findElements(By.tagName("a")).get(c.get(Calendar.DAY_OF_MONTH)).click();
	}

	public void setEndDate() {
		Calendar c = Calendar.getInstance();		
		Date date = new Date();
		c.setTime(date);
		c.add(Calendar.DATE, 2);
		date = c.getTime();
		wait.waitForElementToBeClickable(nominationEndDateField(), 5);
		nominationEndDateField().click();
		Assert.assertTrue("Date picker did not appear!", datePickerCalendar().isDisplayed());
		datePickerCalendar().findElements(By.tagName("a")).get(c.get(Calendar.DAY_OF_MONTH)).click();
	}

	public void createNomination() {

		while (deleteNomination()) {
			SeleniumTest.logger.info("Removing previous nominations..." + System.lineSeparator());
		}

		// step 1
		createNominationButton().click();
		selectEvent(1);
		selectType(1);
		submitNominationButton().click();

		SeleniumTest.logger.info("Creating nomination..." + System.lineSeparator());

		// step 2
		nominationTitleField().sendKeys("QA nomination");
		setEndDate();
		setStartDate();
		saveNominationButton().click();
		Assert.assertTrue("Success message not present!", wait.waitForPresenceOf(By.id("successMsg")));

		// verify
		// viewNominationButton().click();
		// Assert.assertTrue("Wrong nomination title!",
		// nominationHeadline().getText().equalsIgnoreCase("QA nomination"));
	}

	public boolean deleteNomination() {

		// step 1
		for (int i = 0; i < nominationsTable().size(); i++) {
			if (nominationsTable().get(i).findElements(By.tagName("td")).size() > 0) {
				if (nominationsTable().get(i).findElements(By.tagName("td")).get(0).getText()
						.contains("QA nomination")) {
					nominationsTable().get(i).findElement(By.linkText("ARCHIVE")).click();
					interrupt.checkAlert();
					Assert.assertTrue("Success message not present!", wait.waitForPresenceOf(By.id("successMsg")));
					return true;
				}
			}
		}

		return false;
	}
}
