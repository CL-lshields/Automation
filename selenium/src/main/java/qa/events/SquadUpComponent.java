package qa.events;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.SeleniumTest;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class SquadUpComponent extends BasePage {
	WaitTool wait;
	InterruptTool interrupt;

	public SquadUpComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	public void toPage(String username, String password) {
		driver.navigate().to("http://" + username + ":" + password + "@" + "staging.squadup.com/");
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement SquadUpHomeLogo() {
		return driver.findElement(By.className("logo"));
	}

	private WebElement cancelEventButton() {
		return driver.findElement(By.id("host-controls")).findElements(By.tagName("a")).get(2);
	}

	private WebElement squadUpSignInButton() {
		return driver.findElement(By.id("appzi-navigation")).findElement(By.linkText("Sign In"));
	}

	private WebElement squadUpEmailField() {
		List<WebElement> temp = driver.findElement(By.id("signin-modal")).findElements(By.tagName("input"));
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getAttribute("type").equalsIgnoreCase("text")) {
				return temp.get(i);
			}
		}
		return null;
	}

	private WebElement squadUpPasswordField() {
		List<WebElement> temp = driver.findElement(By.id("signin-modal")).findElements(By.tagName("input"));
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getAttribute("type").equalsIgnoreCase("password")) {
				return temp.get(i);
			}
		}
		return null;
	}

	private WebElement createEventButton() {
		return driver.findElement(By.id("secondary-nav")).findElements(By.tagName("li")).get(0);
	}

	private WebElement eventNameField() {
		return driver.findElement(By.id("event-name"));
	}

	private WebElement eventLocationField() {
		return driver.findElement(By.id("location"));
	}

	private WebElement locationNameField() {
		return driver.findElement(By.id("location-name"));
	}

	private WebElement startDateField() {
		return driver.findElement(By.id("start-date"));
	}

	private WebElement endDateField() {
		return driver.findElement(By.id("end-date"));
	}

	private List<WebElement> eventDatePicker() {
		return driver.findElement(By.id("ui-datepicker-div")).findElements(By.tagName("td"));
	}

	private WebElement startTimeField() {
		return driver.findElement(By.id("start-time"));
	}

	private WebElement endTimeField() {
		return driver.findElement(By.id("end-time"));
	}

	private WebElement eventDescriptionField() {
		return driver.findElement(By.cssSelector(
				"html.wf-fftisasanswebpro-n4-active.wf-fftisasanswebpro-n7-active.wf-active body.events.new div#wrap div#create-event div.container "
						+ "div.row div.col-md-12.col-sm-12.col-xs-12 form#create-event-frm div#describe-event.row div.col-md-6.col-md-offset-3.col-sm-8.col-sm-offset-2.col-xs-12 div.form-group "
						+ "span.hidden-xs div.froala-box.squadup-green-theme div.froala-wrapper.f-basic"
						+ " div.froala-view.froala-element.not-msie.f-basic"));
	}

	private WebElement eventTeaserField() {
		return driver.findElement(By.cssSelector(
				"html.wf-fftisasanswebpro-n4-active.wf-fftisasanswebpro-n7-active.wf-active body.events.new div#wrap "
						+ "div#create-event div.container div.row div.col-md-12.col-sm-12.col-xs-12 form#create-event-frm div#describe-event.row div.col-md-6.col-md-offset-3.col-sm-8.col-sm-offset-2.col-xs-12 "
						+ "div.form-group textarea#teaser.form-control"));
	}

	private WebElement eventDepartmentField() {
		return driver.findElement(By.id("event_department_id"));
	}

	private WebElement eventCategoryField() {
		return driver.findElement(By.id("event_category_id"));
	}

	private WebElement eventSubCategoryField() {
		return driver.findElement(By.cssSelector(
				"html.wf-fftisasanswebpro-n4-active.wf-fftisasanswebpro-n7-active.wf-active body.events.new div#wrap div#create-event div.container div.row "
						+ "div.col-md-12.col-sm-12.col-xs-12 form#create-event-frm div#describe-event.row div.col-md-6.col-md-offset-3.col-sm-8.col-sm-offset-2.col-xs-12 div.form-group "
						+ "ul.list-unstyled.subcategory-list li div.input-group select.form-control"));
	}

	private WebElement eventTopicsField() {
		return driver.findElement(By.id("event-tags"));
	}

	private WebElement rsvpDateField() {
		return driver.findElement(By.id("rsvp-date"));
	}

	private WebElement rsvpTimeField() {
		return driver.findElement(By.id("rsvp-time"));
	}

	private WebElement sellTicketsButton() {
		return driver.findElement(By.className("ticketed"));
	}

	private WebElement ticketsTable() {
		return driver.findElement(By.id("create-tickets"));
	}

	private WebElement freeTicketButton() {
		return driver.findElement(By.id("free-ticket"));
	}

	private WebElement freeTicketName() {
		return driver.findElement(By.id("ticket-name"));
	}

	private WebElement freeTicketQty() {
		return driver.findElement(By.id("ticket-quantity"));
	}

	private WebElement freeTicketSave() {
		return driver.findElement(By.id("create-ticket"));
	}

	private WebElement paidTicketButton() {
		return driver.findElement(By.id("paid-ticket"));
	}

	private WebElement paidTicketName() {
		return driver.findElement(By.id("ticket-name"));
	}

	private WebElement paidTicketPrice() {
		return driver.findElement(By.id("ticket-price"));
	}

	private WebElement paidTicketQty() {
		return driver.findElement(By.id("ticket-quantity"));
	}

	private WebElement promoCodeButton() {
		return driver.findElement(By.id("add-promo-code"));
	}

	private WebElement promoCodeName() {
		return driver.findElement(By.id("promo-code"));
	}

	private WebElement promoCodeQty() {
		return driver.findElement(By.id("promo-quantity"));
	}

	private WebElement promoCodePerOrderCheck() {
		return driver.findElement(By.name("ko_unique_3"));
	}

	private WebElement promoCodePerTicketCheck() {
		for (int i = 0; i < ticketsTable().findElements(By.tagName("input")).size(); i++) {
			if (ticketsTable().findElements(By.tagName("input")).get(i).getAttribute("value").contains("per_ticket")) {
				return ticketsTable().findElements(By.tagName("input")).get(i);
			}
		}
		return null;
	}

	private WebElement promoCodePerTicketTable() {
		return driver.findElement(By.id("ms-promo-tickets")).findElement(By.className("ms-selectable"));
	}

	private WebElement promoCodeAmountPercent() {
		return driver.findElement(By.id("promo-amount-percentage"));
	}

	private WebElement savePromoCode() {
		return driver.findElement(By.id("create-promo"));
	}

	private WebElement paidTicketSave() {
		return driver.findElement(By.id("create-ticket"));
	}

	private WebElement publishEventButton() {
		return driver.findElement(By.className("ladda-label"));
	}

	private WebElement customizeEventButton() {
		return driver.findElement(By.id("customize-event"));
	}

	private WebElement addSponsorTierButton() {
		driver.findElements(By.tagName("button"));
		for (int i = 0; i < driver.findElements(By.tagName("button")).size(); i++) {
			if (driver.findElements(By.tagName("button")).get(i).getText().equals("+ ADD SPONSORSHIP TIER")) {
				return driver.findElements(By.tagName("button")).get(i);
			}
		}
		return null;
	}

	private WebElement saveSponsorTierButton() {
		return driver.findElement(By.id("event-sponsor-tier-form")).findElement(By.className("btn-block"));
	}

	private WebElement sponsorTierNameField() {
		return driver.findElement(By.id("sponsor-tier-name"));
	}

	private WebElement addSponsorButton() {
		driver.findElements(By.tagName("button"));
		for (int i = 0; i < driver.findElements(By.tagName("button")).size(); i++) {
			if (driver.findElements(By.tagName("button")).get(i).getText().equals("+ ADD SPONSORSHIP")) {
				return driver.findElements(By.tagName("button")).get(i);
			}
		}
		return null;
	}

	private WebElement sponsorNameField() {
		return driver.findElement(By.id("sponsor-name"));
	}

	private WebElement sponsorDescriptionField() {
		return driver.findElement(By.id("sponsor-description"));
	}

	private WebElement sponsorUrlField() {
		return driver.findElement(By.id("sponsor-destination-url"));
	}

	private WebElement sponsorLogoButton() {
		return driver.findElement(By.id("event-sponsor-form")).findElement(By.className("btn-green"));
	}

	private WebElement saveSponsorButton() {
		return driver.findElement(By.id("event-sponsor-form")).findElement(By.className("btn-block"));
	}

	private WebElement imageLinkButton() {
		return driver.findElement(By.className("sidebar__group")).findElement(By.className("sbicon-download"));
	}
	
	private WebElement imageInstagramButton() {
		return driver.findElement(By.className("sidebar__group")).findElements(By.tagName("li")).get(8);
	}

	private WebElement imageLinkField() {
		return driver.findElement(By.id("linkAddress"));
	}

	private WebElement imageLinkSearch() {
		return driver.findElements(By.className("form__group")).get(1).findElement(By.className("btn--primary"));
	}

	private WebElement imageLinkSelect() {
		return driver.findElement(By.id("e2e_url_select"));
	}
	
	private WebElement iframeSideNavToggle(){
		return driver.findElement(By.className("toggle-sidenav"));
	}

	private WebElement imageLinkSave() {
		return driver.findElement(By.className("action-bar__buttons")).findElement(By.className("ng-binding"));
	}

	private WebElement sponsorshipTierDropdown() {
		return driver.findElement(By.id("sponsor-tier"));
	}

	// --------------------------Helpers-------------------------------------//

	private void addSponsorshipTier(String name) {
		// customizeEventButton().click();
		interrupt.scrollIntoView(driver.findElement(By.id("question-creation")));
		addSponsorTierButton().click();
		sponsorTierNameField().sendKeys(name);
		interrupt.scrollIntoView(sponsorTierNameField());
		saveSponsorTierButton().click();
		SeleniumTest.logger.info("Sponsor Tier " + name + " created..." + System.lineSeparator());		
	}

	private void addSponsorship(HashMap<String, String> data, int i) {
		// add sponsor
		interrupt.scrollIntoView(addSponsorTierButton());
		addSponsorButton().click();
		sponsorNameField().sendKeys("sp#1");
		sponsorDescriptionField().sendKeys("sp#1");
		sponsorUrlField().sendKeys(data.get("client"));
		interrupt.scrollIntoView(sponsorDescriptionField());
		sponsorLogoButton().click();
		wait.waitForPresenceOf(By.id("filepicker_dialog"));
		driver.switchTo().frame("filepicker_dialog");
		if(wait.waitForPresenceOf(By.className("toggle-sidenav"))){			
			iframeSideNavToggle().click();
		}		
		interrupt.jsClick(imageLinkButton());//.click();		
		imageLinkField().sendKeys(
				"http://thumb101.shutterstock.com/display_pic_with_logo/481717/215348707/stock-vector-sponsor-stamp-215348707.jpg");
		imageLinkSearch().click();
		imageLinkSelect().click();
		wait.sleep(5000);
		wait.waitForPresenceOf(By.className("ng-binding"));
		imageLinkSave().click();
		wait.sleep(5000);
		driver.switchTo().defaultContent();
		wait.waitForElementToBeClickable(sponsorshipTierDropdown(), 10);
		sponsorshipTierDropdown().click();
		sponsorshipTierDropdown().findElements(By.tagName("option")).get(i).click();
		interrupt.scrollIntoView(sponsorLogoButton());
		wait.waitForElementToBeClickable(saveSponsorButton(), 10);
		saveSponsorButton().click();
		SeleniumTest.logger.info("Sponsor added..." + System.lineSeparator());
	}

	public void navigateToSquadUp(String username, String password) {
		// Navigate to Squadup events page
		toPage(username, password);
	}

	public void setEventDepartment(String category) {
		eventCategoryField().click();
		for (int i = 0; i < eventDepartmentField().findElements(By.tagName("option")).size(); i++) {
			if (eventDepartmentField().findElements(By.tagName("option")).get(i).getText().equalsIgnoreCase(category)) {
				eventDepartmentField().findElements(By.tagName("option")).get(i).click();
			}
		}
	}

	public void setEventDepartment(int i) {
		eventDepartmentField().click();
		eventDepartmentField().findElements(By.tagName("option")).get(i).click();
		;
	}

	public void setEventCategory(String category) {
		eventCategoryField().click();
		for (int i = 0; i < eventCategoryField().findElements(By.tagName("option")).size(); i++) {
			if (eventCategoryField().findElements(By.tagName("option")).get(i).getText().equalsIgnoreCase(category)) {
				eventCategoryField().findElements(By.tagName("option")).get(i).click();
			}
		}
	}

	public void setEventCategory(int i) {
		eventCategoryField().click();
		eventCategoryField().findElements(By.tagName("option")).get(i).click();
	}

	public void setEventSubCategory(String category) {
		eventCategoryField().click();
		for (int i = 0; i < eventSubCategoryField().findElements(By.tagName("option")).size(); i++) {
			if (eventSubCategoryField().findElements(By.tagName("option")).get(i).getText()
					.equalsIgnoreCase(category)) {
				eventSubCategoryField().findElements(By.tagName("option")).get(i).click();
			}
		}
	}

	public void setEventSubCategory(int i) {
		eventSubCategoryField().click();
		eventSubCategoryField().findElements(By.tagName("option")).get(i).click();
	}

	public void setStartDate() {

		// determine date
		boolean found=false;
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		date = c.getTime();
		c.add(Calendar.DATE, 2);

		//
		startDateField().click();
		for (int i = 0; i < eventDatePicker().size(); i++) {				
			if (eventDatePicker().get(i).getText().equalsIgnoreCase(Integer.toString(c.get(Calendar.DAY_OF_MONTH)))) {
				if (eventDatePicker().get(i).getAttribute("class").contains("unselectable")) {
					driver.findElement(By.className("ui-datepicker-next")).click();
					i = 0;
				}else{	
					eventDatePicker().get(i).click();		
					found = true;
					break;
				}
			}
		}			
	}

	public void setEndDate() {
		boolean nextMonth = true;

		// determine date
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		c.add(Calendar.DATE, 2);
		// date = c.getTime();

		endDateField().click();
		for (int i = 0; i < eventDatePicker().size(); i++) {
			if (eventDatePicker().get(i).getText().equalsIgnoreCase(Integer.toString(c.get(Calendar.DAY_OF_MONTH)))) {
				eventDatePicker().get(i).click();
				nextMonth = false;
			}
		}

		if (nextMonth) {
			driver.findElement(By.className("ui-datepicker-next")).click();
			for (int i = 0; i < eventDatePicker().size(); i++) {
				if (eventDatePicker().get(i).getText()
						.equalsIgnoreCase(Integer.toString(c.get(Calendar.DAY_OF_MONTH)))) {
					eventDatePicker().get(i).click();
					nextMonth = false;
				}
			}
		}
	}

	/**
	 * sign into squadup admin
	 */
	public void signInToSquadUp(HashMap<String, String> data) {
		wait.waitForElementToBeClickable(squadUpSignInButton(), 10);
		wait.waitForVisibilityOf(squadUpSignInButton(), 10);
		wait.waitForPresenceOf(By.linkText("Sign In"));
		squadUpSignInButton().sendKeys(Keys.ENTER);
		;

		wait.waitForElementToBeClickable(squadUpEmailField(), 10);
		squadUpEmailField().sendKeys(data.get("su-account"));
		wait.waitForElementToBeClickable(squadUpPasswordField(), 10);
		squadUpPasswordField().sendKeys("squadup");
		squadUpPasswordField().sendKeys(Keys.ENTER);
	}	

	/**
	 * crate an event using squadups admin interface, using the data from the
	 * supplied hashmap.
	 * 
	 * @param data
	 *            - hashmap provided from test case, usually an instance of
	 *            ExcelDriver.
	 */
	public void createEvent(HashMap<String, String> data) {
		createEventButton().click();

		//
		wait.waitForElementToBeClickable(eventNameField(), 10);
		eventNameField().sendKeys("SU QA event");
		eventLocationField().sendKeys(data.get("market"));
		eventLocationField().sendKeys(Keys.ENTER);
		interrupt.scrollIntoView(eventNameField());
		setStartDate();
		startTimeField().sendKeys("8:00 pm");
		startTimeField().sendKeys(Keys.ENTER);
		interrupt.scrollIntoView(startTimeField());
		setEndDate();
		endTimeField().sendKeys("8:00pm");
		endTimeField().sendKeys(Keys.ENTER);
		setEventDepartment(3);
		setEventCategory(1);
		setEventSubCategory(1);
		interrupt.scrollIntoView(startTimeField());
		eventTeaserField().sendKeys(data.get("client") + " " + data.get("address"));
		eventDescriptionField().sendKeys(data.get("client"));
		eventTopicsField().findElement(By.tagName("input")).sendKeys("workshop");
		eventTopicsField().sendKeys(Keys.ENTER);
		endTimeField();
		rsvpTimeField();
		rsvpDateField();
		locationNameField();
		// sellTicketsButton().click();
		interrupt.scrollIntoView(sellTicketsButton());

		// free tickets
		freeTicketButton().sendKeys(Keys.ENTER);
		freeTicketName().sendKeys("QA Free Tiks");
		freeTicketQty().sendKeys("100");
		freeTicketSave().sendKeys(Keys.ENTER);

		// paid tickets
		paidTicketButton().sendKeys(Keys.ENTER);
		paidTicketName().sendKeys("QA Tiks");
		paidTicketPrice().sendKeys("100");
		paidTicketQty().sendKeys("100");
		paidTicketSave().sendKeys(Keys.ENTER);

		// paid tickets
		paidTicketButton().sendKeys(Keys.ENTER);
		paidTicketName().sendKeys("QA No Promo Tiks");
		paidTicketPrice().sendKeys("100");
		paidTicketQty().sendKeys("100");
		paidTicketSave().sendKeys(Keys.ENTER);		

		// promo code per order
		interrupt.scrollIntoView(sellTicketsButton());
		promoCodeButton().click();
		promoCodeName().sendKeys("QAPO25P");
		promoCodeQty().sendKeys("100");
		promoCodeAmountPercent().sendKeys("25");
		wait.waitForElementToBeClickable(promoCodePerOrderCheck(), 5);
		interrupt.scrollIntoView(promoCodeQty());
		promoCodePerOrderCheck().click();
		interrupt.scrollIntoView(promoCodeQty());
		savePromoCode().sendKeys(Keys.ENTER);

		// promo code per ticket
		interrupt.scrollIntoView(ticketsTable());
		promoCodeButton().click();
		promoCodeName().sendKeys("ALLTIKSOFF50");
		promoCodeQty().sendKeys("100");
		promoCodeAmountPercent().sendKeys("50");
		interrupt.scrollIntoView(promoCodeQty());
		promoCodePerTicketCheck().click();
		promoCodePerTicketTable().findElements(By.tagName("li")).get(0).click();
		savePromoCode().sendKeys(Keys.ENTER);

		// promo code per ticket
		interrupt.scrollIntoView(ticketsTable());
		promoCodeButton().click();
		promoCodeName().sendKeys("QAPT50P");
		promoCodeQty().sendKeys("100");
		promoCodeAmountPercent().sendKeys("50");
		interrupt.scrollIntoView(promoCodeQty());
		promoCodePerTicketCheck().click();
		for (int i = 0; i < promoCodePerTicketTable().findElements(By.tagName("li")).size(); i++) {
			if (!promoCodePerTicketTable().findElements(By.tagName("li")).get(i).getText().contains("No Promo")
					&& !promoCodePerTicketTable().findElements(By.tagName("li")).get(i).getText()
							.contains("All Tickets")) {
				promoCodePerTicketTable().findElements(By.tagName("li")).get(i).click();
			}
		}
		interrupt.scrollIntoView(promoCodePerTicketCheck());
		savePromoCode().sendKeys(Keys.ENTER);

		// sponsorship tier
		customizeEventButton().click();
		addSponsorshipTier("Tier One");		
		addSponsorship(data, 1);		

		publishEventButton().click();
		Assert.assertTrue("Event may not have published correctly!",
				wait.waitForPresenceOf(By.id("host-controls"))
				/*driver.findElement(By.id("host-controls")).isDisplayed()*/);
		SeleniumTest.logger.info("Created SquadUp event..." + System.lineSeparator());
		SquadUpHomeLogo().click();

	}

	/**
	 * Delete all QA events from the squadup events list
	 */
	public void deleteQAEvents() {
		String cancelString = "html.wf-fftisasanswebpro-n4-active.wf-fftisasanswebpro-n7-active.wf-active "
				+ "body.events.show.modal-open div.bootbox.modal.fade.in div.modal-dialog div.modal-content "
				+ "div.modal-footer button.btn.btn-danger";

		// get list of row elements. All events are contained within the rows
		int count = 0;
		int errCount = 0;

		// loop through events for QA events
		for (int i = 0; i < driver.findElements(By.tagName("a")).size(); i++) { // driver.findElements(By.tagName("a")).size()

			// try-catch to refresh the page if encountering stale elements
			try {

				// delete all the QA events we find. Ignore all else
				if (driver.findElements(By.tagName("a")).get(i).getText().equals("SU QA event")) {

					// QA event alert
					SeleniumTest.logger.info("found Squad Up event to delete..." + System.lineSeparator());

					driver.findElements(By.tagName("a")).get(i).click();
					// driver.findElements(By.linkText("SU QA
					// Event")).get(i).click();
					cancelEventButton().click();
					wait.waitForPresenceOf(By.cssSelector(cancelString));
					wait.waitForElementToBeClickable(driver.findElement(By.cssSelector(cancelString)), 10);
					driver.findElement(By.cssSelector(cancelString)).click();
					Assert.assertTrue("event may not have been deleted correctly!",
							driver.findElement(By.id("manage-events")).isDisplayed());
					count++;
					i = 0;
					wait.waitForPresenceOf(By.id("manage-events"));
					driver.navigate().refresh();
					SeleniumTest.logger.info("Removed " + count + " QA event(s)" + System.lineSeparator());
				}
			} catch (StaleElementReferenceException ex) {
				i = 0;
				errCount++;
				if (errCount > 3) {
					Assert.fail("Stale Element in squadup events page. Rerunning may resolve this issue");
				}
				SeleniumTest.logger.info(
						"Stale Element encountered in squadup...(" + errCount + ") times" + System.lineSeparator());
				driver.navigate().refresh();
				// Assert.fail("Stale Element in squadup events page. Rerunning
				// may resolve this issue");
			}
		}

	}

	/**
	 * Delete a specific event from the squadup events list
	 * 
	 * @param eventName
	 *            - name of the event to delete
	 */
	public void deleteEvent(String eventName) {
		// TODO
	}

}
