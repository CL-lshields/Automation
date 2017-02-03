package qa.events;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import qa.SeleniumTest;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class EventComponent extends BasePage {
	WaitTool wait;
	InterruptTool interrupt;

	public EventComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	@Override
	public boolean atPage() {
		return driver.findElement(By.tagName("title")).isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private String pageTitleHeader() {
		return driver.getTitle();
	}

	private List<WebElement> featuredEventsTable() {
		return driver.findElements(By.className("item--flag"));
	}

	private WebElement registerButton() {
		return driver.findElement(By.id("register")).findElement(By.className("module"))
				.findElement(By.className("module__body")).findElement(By.className("ticket-type"))
				.findElement(By.className("row")).findElements(By.tagName("button")).get(0);
	}

	private WebElement eventSearchBox() {
		return driver.findElement(By.id("eventKeyword"));
	}

	private WebElement startDateBox() {
		return driver.findElement(By.name("searchstart"));
	}

	private WebElement endDateBox() {
		return driver.findElement(By.name("searchend"));
	}

	private WebElement searchSubmitButton() {
		return driver.findElement(By.className("eventSearchButton"));
	}

	private WebElement bizJournalsEventsSortLink() {
		return driver.findElements(By.className("pull-right")).get(0);
	}

	////////////////////// SquadUp Registration Elements////////////////////////

	private WebElement squadUpFirstNameField() {
		return driver.findElement(By.name("first_name"));
	}

	private WebElement squadUpLastNameField() {
		return driver.findElement(By.name("last_name"));
	}

	private WebElement SquadUpEmailField() {
		return driver.findElement(By.name("email"));
	}

	private WebElement SquadUpPasswordField() {
		return driver.findElement(By.name("password"));
	}

	private WebElement creditCardField() {
		return driver.findElement(By.id("credit-card-number"));
	}

	private WebElement cvvField() {
		return driver.findElement(By.id("cvv"));
	}

	private WebElement expirationDateField() {
		return driver.findElement(By.id("expiration"));
	}

	private WebElement getTicketsButton() {
		return driver.findElement(By.className("btn.squadup-checkout-submit-payment"));
	}

	// --------------------------Helpers-------------------------------------//

	public void setTicketQuantity() {

	}

	public void setSquadUpFirstName(String fName) {
		squadUpFirstNameField().sendKeys(fName);
	}

	public void setsquadUpLastName(String lName) {
		squadUpLastNameField().sendKeys(lName);
	}

	public void setSquadUpEmail(String email) {
		SquadUpEmailField().sendKeys(email);
	}

	public void setsquadUpPassword(String password) {
		SquadUpPasswordField().sendKeys(password);
	}

	public void setCreditCard(String credit) {
		creditCardField().sendKeys(credit);
	}

	public void setCvv(int cvv) {
		cvvField().sendKeys(Integer.toString(cvv));
	}

	public void setExpirationDate(String exp) {
		expirationDateField().sendKeys(exp);
	}

	public void getTickets() {
		getTicketsButton().click();
	}

	/**
	 * Navigates to page that only displays BizJournals events and incorporates
	 * search functionality.
	 */
	public void displayBizjEvents() {
		bizJournalsEventsSortLink().click();
		SeleniumTest.logger.info("Displaying Biz Journals events only..." + System.lineSeparator());
	}

	/**
	 * Performs check to see if event is in the sold out status
	 * 
	 * @return
	 */
	private boolean soldOutEvent() {
		try {
			String check = driver.findElement(By.className("event__registration-message"))
					.findElement(By.className("event__registration-message__title")).getText().toLowerCase();
			if (check.contains("sold out")) {
				SeleniumTest.logger.info("Event is sold out..." + System.lineSeparator());
				return true;
			}
		} catch (Exception ex) {
			Assert.assertTrue("Test may have encountered incorrect page!",
					ex.getMessage().toLowerCase().contains("event__registration-message"));
		}
		return false;
	}

	/**
	 * attempts to find a Bizjournal event in the events table.
	 */
	public void selectBizJournalEvent() {
		String verify = null;
		Assert.assertTrue("Featured events table not present...",wait.waitForPresenceOf(By.className("eventFeaturedTable")));
		for (int i = 0; i < featuredEventsTable().size(); i++) {
			if (featuredEventsTable().get(i).findElement(By.tagName("button")).getText().equalsIgnoreCase("register")) {
				verify = featuredEventsTable().get(i).findElement(By.className("fEventname"))
						.findElement(By.tagName("a")).getText();
				featuredEventsTable().get(i).findElement(By.tagName("button")).click();
				if (soldOutEvent()) {
					driver.navigate().back();
				} else {
					break;
				}
			}
		}
		
		Assert.assertTrue("Incorrect Event!", driver.findElement(By.id("boundary0")).findElement(By.className("hero"))
				.findElement(By.tagName("h1")).getText().equalsIgnoreCase(verify));
		SeleniumTest.logger.info("Event component: BizJournal event found..." + System.lineSeparator());
	}

	/**
	 * attemps to find a non-BizJournal event on the events table.
	 */
	public void selectNonBizJournalEvent() {
		String verify = null;

		if(driver.findElements(By.className("item--flag")).size()>0){
			// featured events table search
			for (int i = 0; i < featuredEventsTable().size(); i++) {
				if (featuredEventsTable().get(i).findElement(By.className("item__call-to-action")).findElement(By.tagName("a"))
						.getText().equalsIgnoreCase("More Information")) {	
					verify = featuredEventsTable().get(i).findElement(By.className("item__title"))
							.findElement(By.tagName("a")).getText();
					interrupt.scrollIntoView(featuredEventsTable().get(i));
					Actions action = new Actions(driver);					 
			        action.moveToElement(driver.findElement(By.className("banner"))).click().build().perform();
					featuredEventsTable().get(i).findElement(By.className("item__call-to-action")).findElement(By.tagName("a")).click();
					SeleniumTest.logger.info("Event component: non-BizJournals event found..." + System.lineSeparator());					
					break;
				}
			}
		}
		
//		// check featured events table
//		for (int i = 0; i < featuredEventsTable().size(); i++) {
//			if (featuredEventsTable().get(i).findElement(By.tagName("button")).getText()
//					.equalsIgnoreCase("More Information")) {
//				verify = featuredEventsTable().get(i).findElement(By.className("fEventname"))
//						.findElement(By.tagName("a")).getText();
//				featuredEventsTable().get(i).findElement(By.tagName("button")).click();
//				Assert.assertTrue("Incorrect Event!", pageTitleHeader().contains(verify));
//				SeleniumTest.logger.info("Event component: non-BizJournals event found..." + System.lineSeparator());				
//				break;
//			}
//		}
		if (verify != null) {
			Assert.assertTrue("Incorrect Event!", pageTitleHeader().contains(verify));
			driver.navigate().back();
		} else {
			SeleniumTest.logger.info("There was an error with events table..." + System.lineSeparator());
		
//			// check events table
//			for (int i = 0; i < eventsTable().size(); i++) {
//				if (eventsTable().get(i).findElement(By.className("eventButtonPad")).findElement(By.tagName("a"))
//						.getText().equalsIgnoreCase("More Information")) {
//					verify = eventsTable().get(i).findElement(By.className("eventNameData"))
//							.findElement(By.tagName("a")).getText();
//					eventsTable().get(i).findElement(By.className("eventButtonPad")).findElement(By.tagName("a"))
//							.click();
//					Assert.assertTrue("Incorrect Event!", pageTitleHeader().contains(verify));
//					SeleniumTest.logger
//							.info("Event component: non-BizJournals event found..." + System.lineSeparator());
//					driver.navigate().back();
//					break;
//				}
//			}
		}		
	}

	/**
	 * Selects a specific event as specified by integer i.
	 * 
	 * @param event
	 *            - integer number of event to select.
	 */
	public void selectEvent(int event) {
		String verify = null;
		verify = featuredEventsTable().get(event).findElement(By.className("fEventname")).findElement(By.tagName("a"))
				.getText();
		featuredEventsTable().get(event).findElement(By.tagName("button")).click();
		Assert.assertTrue("Incorrect Event!",
				driver.findElement(By.className("hero_title")).getText().equalsIgnoreCase(verify));
		SeleniumTest.logger.info("Event component: event " + event + " found..." + System.lineSeparator());
	}

	/**
	 * Selects a specific event as specified by string event
	 * 
	 * @param event
	 *            - string representation of event to select.
	 */
	public void selectEvent(String event) {
		boolean found = false;

		if(driver.findElements(By.className("item--flag")).size()>0){
			// featured events table search
			for (int i = 0; i < featuredEventsTable().size(); i++) {
				if(featuredEventsTable().get(i).findElement(By.className("item__title")).findElements(By.tagName("a")).size()>0){
					if (featuredEventsTable().get(i).findElement(By.className("item__title")).findElement(By.tagName("a"))
							.getText().equalsIgnoreCase(event)) {		
						interrupt.scrollIntoView(featuredEventsTable().get(i));
						//Actions action = new Actions(driver);
				        //action.moveToElement(driver.findElement(By.className("banner"))).click().build().perform();
						interrupt.jsClick(featuredEventsTable().get(i).findElement(By.className("item__call-to-action")).findElement(By.tagName("a")));
						//featuredEventsTable().get(i).findElement(By.className("item__call-to-action")).findElement(By.tagName("a")).click();
						SeleniumTest.logger.info("Event component: event " + event + " found..." + System.lineSeparator());
						found = true;
						break;					
					}
				}
			}
		}
		
//		// all events table search
//		
//		for (int i = 1; i < allEventsTable().size(); i++) {			
//			if (allEventsTable().get(i).findElements(By.className("eventNameData")).size() > 0) {				
//				if (allEventsTable().get(i).findElement(By.className("eventNameData")).findElement(By.tagName("a"))
//						.getText().equalsIgnoreCase(event)) {					
//					allEventsTable().get(i).findElement(By.className("eventButtonPad")).findElement(By.tagName("a"))
//							.click();
//					SeleniumTest.logger.info("Event component: event " + event + " found..." + System.lineSeparator());
//					found = true;
//					break;
//				}
//			}
//		}

		if (!found) {
			SeleniumTest.logger.info("no event '" + event + "' found..." + System.lineSeparator());
			Assert.fail("No event found with name: " + event);
		}
	}

	/**
	 * Register for currently selected event
	 */
	public void registerForEvent() {
		registerButton().click();
		SeleniumTest.logger.info("Event component: registering for event..." + System.lineSeparator());
	}

	/**
	 * Performs form validation while simultaneously registering for a squadup
	 * event.
	 * 
	 * @param data
	 *            - hashmap, usually provided by ExcelDriver
	 */
	public void squadUpEventRegistration(HashMap<String, String> data) {
		setSquadUpFirstName(data.get("username"));
		setsquadUpLastName(data.get("username"));
		this.setSquadUpEmail(data.get("username"));
		this.setsquadUpPassword(data.get("password"));
		this.setCreditCard("credit");
		this.setCvv(Integer.parseInt(data.get("cvv")));
		this.setExpirationDate("expiration");
		this.getTickets();
		SeleniumTest.logger.info("registered for squadUp event in: " + data.get("market") + System.lineSeparator());
	}

	/////////////////////////////////// Search Test
	/////////////////////////////////// Functionality/////////////////////////////////////
	public boolean searchEvent(String search) {
		eventSearchBox().sendKeys(search);
		return true;
	}

	public void setSearchEventDate(String start, String end) {
		startDateBox().sendKeys(start);
		endDateBox().sendKeys(end);
		searchSubmitButton().click();
	}

	public void searchTest() {
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();

		startCalendar.setTime(new java.util.Date());
		endCalendar.setTime(new java.util.Date());
		endCalendar.add(Calendar.MONTH, 3);

		// invalid date
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		setSearchEventDate(dateFormat.format(endCalendar.getTime()), dateFormat.format(startCalendar.getTime()));
		Assert.assertTrue("Unexpected event found",
				driver.findElement(By.className("eventData")).findElement(By.tagName("li")).getText()
						.equalsIgnoreCase("No events found for the dates listed above."));

		// past date
		startCalendar.add(Calendar.YEAR, -4);
		endCalendar.add(Calendar.YEAR, -3);
		setSearchEventDate(dateFormat.format(endCalendar.getTime()), dateFormat.format(endCalendar.getTime()));
		Assert.assertTrue("Unexpected event found",
				driver.findElement(By.className("eventData")).findElement(By.tagName("li")).getText()
						.equalsIgnoreCase("No events found for the dates listed above."));

		// improper date
		setSearchEventDate("35/04/17", dateFormat.format(endCalendar.getTime()));
		Assert.assertTrue("Unexpected event found",
				driver.findElement(By.className("eventData")).findElement(By.tagName("li")).getText()
						.equalsIgnoreCase("No events found for the dates listed above."));

		// valid date
		setSearchEventDate(dateFormat.format(startCalendar.getTime()), dateFormat.format(endCalendar.getTime()));
	}

}
