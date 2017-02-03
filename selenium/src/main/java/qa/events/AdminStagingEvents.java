package qa.events;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.SeleniumTest;
import qa.utility.WaitTool;
import qa.utility.InterruptTool;

public class AdminStagingEvents extends BasePage {
	WaitTool wait;
	InterruptTool interrupt;
	String promo;

	public AdminStagingEvents(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	public String getPromo() {
		return promo = driver.findElement(By.id("promocodes")).findElements(By.tagName("tr")).get(0)
				.findElements(By.tagName("td")).get(0).findElement(By.tagName("p")).getText();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement createNewEventButton() {
		return driver.findElement(By.id("events")).findElement(By.linkText("CREATE NEW EVENT"));
	}

	private WebElement formErrors() {
		return driver.findElement(By.className("mainErrors"));
	}

	private WebElement eventName() {
		return driver.findElement(By.id("eventName"));
	}

	private WebElement eventSubCategory() {
		return driver.findElement(By.id("event_subcategory1"));
	}

	private WebElement eventTopic() {
		return driver.findElement(By.id("event_topic"));
	}

	@SuppressWarnings("unused")
	private WebElement eventMarket() {
		return driver.findElement(By.id("create_market"));
	}

	private WebElement eventType() {
		return driver.findElement(By.id("event_type"));
	}

	private WebElement eventCategory() {
		return driver.findElement(By.id("event_topcategory"));
	}

	private WebElement eventDate() {
		return driver.findElement(By.id("event_date"));
	}

	private WebElement eventTime() {
		return driver.findElement(By.className(""));
	}

	private WebElement eventLocation() {
		return driver.findElement(By.id("locationAddress"));
	}

	private WebElement eventCity() {
		return driver.findElement(By.id("locationCity"));
	}

	private WebElement eventState() {
		return driver.findElement(By.id("locationState"));
	}

	private WebElement eventZip() {
		return driver.findElement(By.id("locationZip"));
	}

	private WebElement contactName() {
		return driver.findElement(By.id("contactName"));
	}

	private WebElement contactEmail() {
		return driver.findElement(By.id("contactEmail"));
	}

	private WebElement contactPhone() {
		return driver.findElement(By.id("contactPhone"));
	}

	private WebElement eventAttendance() {
		return driver.findElement(By.id("attendence"));
	}

	private WebElement eventYears() {
		return driver.findElement(By.name("number_years_held"));
	}

	private WebElement eventSubmit() {
		return driver.findElement(By.id("saveButton"));
	}

	// Events Management Elements

	private WebElement activateEventButton() {
		return driver.findElement(By.id("activateEventButton"));
	}

	private WebElement ticketProductsButton() {
		return driver.findElement(By.id("tab-selector")).findElement(By.linkText("Ticket Product"));
	}

	private WebElement eventDisplayButton() {
		return driver.findElement(By.id("tab-selector")).findElement(By.linkText("Event Display"));
	}
	
	private WebElement marketingButton() {
		return driver.findElement(By.id("tab-selector")).findElement(By.linkText("Marketing"));
	}

	private WebElement newProductButton() {
		return driver.findElement(By.id("new_product_button"));
	}

	private WebElement ticketTitleField() {
		return driver.findElement(By.id("ticketproduct_view")).findElement(By.id("ticketproduct_description"))
				.findElement(By.id("ticketProduct_Desc"));
	}

	private WebElement ticketPriceField() {
		return driver.findElement(By.id("ticketProduct_Price"));
	}

	private WebElement saveTicketButton() {
		return driver.findElement(By.id("ticket_save_button"));
	}

	private WebElement promoCodeButton() {
		return driver.findElement(By.id("add_promo_code_button"));
	}

	private WebElement promoTitleField() {
		return driver.findElement(By.id("promoCode_Description"));
	}

	private WebElement promoPercentField() {
		return driver.findElement(By.id("promoCode_Percent"));
	}

	private WebElement promoUsageField() {
		return driver.findElement(By.id("promoCode_Uses"));
	}

	private WebElement promoDateField() {
		return driver.findElement(By.id("promoCode_Expires"));
	}

	private WebElement savePromoCodeButton() {
		return driver.findElement(By.id("promo_save_button"));
	}

	private WebElement eventUrl() {
		return driver.findElement(By.id("tabs-4")).findElement(By.className("url"));
	}
	
	// Event Status management

	private WebElement viewChangeEventButton() {
		return driver.findElement(By.id("events")).findElement(By.id("secondary-content"))
				.findElement(By.linkText("View / Change Status"));
	}

	private WebElement cancelEventBox() {
		return driver.findElement(By.id("status-cancel"));
	}

	private WebElement statusUpdateButton() {
		return driver.findElement(By.id("statusSubmitButton"));
	}

	private WebElement eventAttributeButton() {
		return driver.findElement(By.id("attributeEditButton"));
	}

	private WebElement eventAttributeDateField() {
		return driver.findElement(By.id("event_date"));
	}

	private WebElement eventAttributeSaveButton() {
		return driver.findElement(By.id("attributeSaveButton"));
	}

	// --------------------------Helpers------------------------------------//

	public void createNewEvent() {
		createNewEventButton().click();
	}

	public void setEventName() {
		eventName().sendKeys("QA Event");
	}

	public void setEventName(String event) {
		eventName().sendKeys(event);
	}

	public void setEventMarket() {

	}

	public void setEventMarket(String event) {

	}

	public void setEventType() {
		eventType().click();
		eventType().findElements(By.tagName("option")).get(1).click();
	}

	public void setEventType(String event) {
		for (int i = 0; i < eventType().findElements(By.tagName("option")).size(); i++) {
			if (eventType().findElements(By.tagName("option")).get(i).getText().equalsIgnoreCase(event)) {
				eventType().findElements(By.tagName("option")).get(i).click();
				break;
			}
		}
	}

	public void setEventSubCategory() {
		eventSubCategory().click();
		eventSubCategory().findElements(By.tagName("option")).get(1).click();
	}

	public void setEventTopic() {
		eventTopic().click();
		eventTopic().findElements(By.tagName("option")).get(1).click();
	}

	public void setEventCategory() {
		eventCategory().click();
		eventCategory().findElements(By.tagName("option")).get(1).click();
	}

	public void setEventCategory(String event) {
		eventName().sendKeys(event);
	}

	public void setEventDate() {
		Calendar c = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		eventDate().sendKeys(dateFormat.format(date));
	}

	public void setEventDate(String date) {
		eventDate().sendKeys(date);
	}

	public void setEventTime() {

	}

	public void setEventTime(String event) {
		eventTime().sendKeys(event);
	}

	public void setEventLocation() {
		eventLocation().sendKeys("400 W Morehead");
	}

	public void setEventLocation(String event) {
		eventLocation().sendKeys(event);
	}

	public void setEventCity() {
		eventCity().sendKeys("QA Town");
	}

	public void setEventCity(String event) {
		eventCity().sendKeys(event);
	}

	public void setEventState() {
		eventState().click();
		eventState().findElements(By.tagName("option")).get(1).click();
	}

	public void setEventState(String event) {

	}

	public void setEventZip() {
		eventZip().sendKeys("30105");
	}

	public void setEventZip(String event) {
		eventZip().sendKeys(event);
	}

	public void setContactName() {
		contactName().sendKeys("QA manager");
	}

	public void setContactName(String event) {
		contactName().sendKeys(event);
	}

	public void setContactEmail() {
		contactEmail().sendKeys("QA@qamail.com");
	}

	public void setContactEmail(String event) {
		contactEmail().sendKeys(event);
	}

	public void setContactPhone() {
		contactPhone().sendKeys("555-555-5555");
	}

	public void setContactPhone(String event) {
		contactPhone().sendKeys(event);
	}

	public void setEventAttendance() {
		eventAttendance().sendKeys("100");
	}

	public void setEventAttendance(String event) {
		eventAttendance().sendKeys(event);
	}

	public void setEventYears() {
		eventYears().click();
		eventYears().findElements(By.tagName("option")).get(1).click();
	}

	public void setEventYears(String event) {
		eventYears().sendKeys(event);
	}

	public void eventConfirmation() {
		eventSubmit().click();
	}

	// Events management helpers

	public void activateEvent() {
		try {
			wait.waitForPresenceOf(By.id("activateEventButton"));
			activateEventButton().click();
		} catch (UnhandledAlertException f) {
			SeleniumTest.logger.info("Alert may be interfering with event creation..." + System.lineSeparator());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
	}

	public void ticketProducts() {
		ticketProductsButton().click();
	}

	public void eventDisplay() {
		eventDisplayButton().click();
	}
	
	public void marketing() {
		marketingButton().click();
	}

	public void createNewTicketProduct() {
		newProductButton().click();
	}

	public void setTicketTitle() {
		ticketTitleField().sendKeys("QA ticket");
	}

	public void setTicketTitle(String name) {
		ticketTitleField().sendKeys(name);
	}

	public void setTicketPrice() {
		ticketPriceField().sendKeys("90");
		;
	}

	public void setTicketPri(String price) {
		ticketPriceField().sendKeys(price);
		;
	}

	public void saveTicket() {
		saveTicketButton().click();
	}

	/**
	 * Cancels the most recent event names 'QA Event' in the events panel of the
	 * admin staging enviornment.
	 */
	public void cancelEvent() {
		boolean eventFound = false;
		int count = 0;
		String event = "";
		for (int i = 0; i < driver.findElement(By.id("eventslist")).findElements(By.tagName("tr")).size(); i++) {
			if (eventFound) {
				i = 0;
				eventFound = false;
			}

			try {
				event = driver.findElement(By.id("eventslist")).findElements(By.tagName("tr")).get(i)
						.findElements(By.tagName("td")).get(4).getText();
			} catch (Exception ex) {
				SeleniumTest.logger.info("No events currently in this market..." + System.lineSeparator());
			}

			// if QA Event, delete
			if (event.equalsIgnoreCase("QA Event")) {
				driver.findElement(By.id("eventslist")).findElements(By.tagName("tr")).get(i)
						.findElements(By.tagName("td")).get(6).findElement(By.linkText("MANAGE")).click();
				eventDisplay();
				viewChangeEventButton().click();
				(new WebDriverWait(driver, 10))
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("status-cancel")));
				cancelEventBox().click();
				wait.waitForVisibilityOf(statusUpdateButton(), 10);
				statusUpdateButton().click();
				eventAttributeButton().click();
				(new WebDriverWait(driver, 10))
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("attributeSaveButton")));
				// calendar
				Calendar c = Calendar.getInstance();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				c.setTime(date);
				c.add(Calendar.DATE, -30);
				date = c.getTime();
				eventAttributeDateField().sendKeys(dateFormat.format(date));
				eventAttributeSaveButton().click();
				driver.findElement(By.id("globalnav-event")).click();
				eventFound = true;
				count++;
				SeleniumTest.logger.info(count + " QA events found and removed" + System.lineSeparator());

			}
		}
		if (count == 0) {
			SeleniumTest.logger.warning("No more events to delete - " + this.getClass() + System.lineSeparator());
		}
	}

	/**
	 * Create a promotion for use in event checkout.
	 * 
	 * @param name
	 *            - name describing what the promotion is for. This is defferent
	 *            from the promotions code, which will be automatically
	 *            generated and then recorded in the ExcelDriver hashmap.
	 * @param percent
	 *            - percentage of the discount, usually provided in the
	 *            ExcelDriver hashmap
	 */
	public void createPromo(String name, String percent) {
		wait.waitForPresenceOf(By.id("add_promo_code_button"));
		interrupt.scrollIntoView(ticketProductsButton());
		wait.waitForVisibilityOf(promoCodeButton(), 10);
		promoCodeButton().click();
		promoTitleField().sendKeys(name);
		promoPercentField().sendKeys(percent);
		promoUsageField().sendKeys("1");
		// Date format
		Calendar c = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		promoDateField().sendKeys(dateFormat.format(date));
		savePromoCodeButton().click();
		wait.waitForPresenceOf(By.className("discountBuffer"));
		Assert.assertTrue("promotion not created properly: " + getPromo(),
				!getPromo().equalsIgnoreCase("No promo codes exist for this event."));
		SeleniumTest.logger.info("Event promotion code created..." + System.lineSeparator());
	}

	// Created a standard event for use in testing
	public void sumbitStandardEvent() {
		setEventName();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventType("Events");
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventCategory();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventSubCategory();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventTopic();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventDate();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventLocation();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventCity();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventState();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventZip();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setContactName();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setContactEmail();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setContactPhone();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventCategory();
		eventConfirmation();
		Assert.assertTrue("Form submitted while incomplete!", formErrors().isDisplayed());
		setEventAttendance();
		eventConfirmation();
		Assert.assertTrue("Wrong page Encountered!", wait.waitForPresenceOf(By.id("activateEventButton")));

		// Finalize Event parameters
		ticketProducts();
		createNewTicketProduct();
		eventDisplay();
		ticketProducts();
		wait.waitForPresenceOf(By.id("ticketProduct_Desc"));
		setTicketTitle();
		setTicketPrice();
		saveTicket();
		activateEvent();
		Alert alert = driver.switchTo().alert();
		alert.accept();

		// verify
		WebElement verify = driver.findElement(By.id("activeDisplay"));
		Assert.assertTrue("Error: " + verify.getText(), verify.getText().equalsIgnoreCase("event active"));
		SeleniumTest.logger.info("Standard event created..." + System.lineSeparator());
	}

	// TODO - creates a specialized event for use in testing
	public void submitCustomEvent() {

	}

	/**
	 * retreives the URL of active squadup event from admin and navigates to the
	 * page.
	 * 
	 */
	public void getSquadUpEvent() {
		String event="";		
		for (int i = 0; i < driver.findElement(By.id("eventslist")).findElements(By.tagName("tr")).size(); i++) {
			boolean active=false;
			try {
				event = driver.findElement(By.id("eventslist")).findElements(By.tagName("tr")).get(i)
						.findElements(By.tagName("td")).get(4).getText();
				if(driver.findElement(By.id("eventslist")).findElements(By.tagName("tr")).get(i) 
						.findElements(By.tagName("td")).get(2).getText().equalsIgnoreCase("active") ^
				driver.findElement(By.id("eventslist")).findElements(By.tagName("tr")).get(i)
				.findElements(By.tagName("td")).get(2).getText().equalsIgnoreCase("today")){
					active = true;
				}				
			} catch (Exception ex) {
				SeleniumTest.logger.info("No events currently in this market..." + System.lineSeparator());
			}
			
			if (event.equalsIgnoreCase("SU QA Event")&&active) {
				driver.findElement(By.id("eventslist")).findElements(By.tagName("tr")).get(i)
						.findElements(By.tagName("td")).get(6).findElement(By.linkText("MANAGE")).click();
				marketing();
				event = eventUrl().getText().replace("www", "staging");
				driver.navigate().to(event);
				SeleniumTest.logger.info("Navigating to: "+event + System.lineSeparator());
				Assert.assertTrue("Couldnt find squadup checkout widget...", wait.waitForPresenceOf(By.id("squadup-checkout")));				
				break;
			}			
		}
		//SeleniumTest.logger.info("Could not find active squadup event... "+event + System.lineSeparator());
	}

}
