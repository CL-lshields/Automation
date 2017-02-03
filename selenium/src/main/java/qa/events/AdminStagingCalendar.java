package qa.events;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class AdminStagingCalendar extends BasePage {	
	WaitTool wait;
	InterruptTool interrupt;

	public AdminStagingCalendar(WebDriver driver) {
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
	
	private WebElement addCalendarListingButton(){
		return driver.findElement(By.linkText("ADD CALENDAR LISTING"));
	}
	
	private WebElement calendarTable(){
		return driver.findElement(By.id("main-content-col1")).findElement(By.tagName("tbody"));
	}

	@SuppressWarnings("unused")
	private WebElement editEventButton(){
		return driver.findElement(By.id("editButton"));
	}
	
	//Start calendar listing creation elements
	private WebElement eventTitleField(){
		return driver.findElement(By.name("title"));
	}
	
	private WebElement eventDetailsField(){
		return driver.findElement(By.cssSelector("html body.multi-market-width.nav__global div#main "
				+ "div#events.clearfix form#saveCalendar div#main-content-col1 div ul.attributes li p "
				+ "div.redactor_box div.redactor_required.redactor_editor"));
	}
	
	private WebElement eventSummaryField(){
		return driver.findElement(By.name("summary"));
	}
	
	private WebElement datePickerBox(){
		return driver.findElement(By.className("datepicker-days"));
	}
	
	private WebElement eventStartDatePicker(){
		return driver.findElement(By.id("cal_date_start"));
	}
	
	private WebElement eventEndDatePicker(){
		return driver.findElement(By.id("cal_date_end"));
	}
	
	private WebElement startTimeDropdown(){
		return driver.findElement(By.name("start_time_hours"));
	}
	
	private WebElement startTimeAmPm(){
		return driver.findElement(By.name("start_time_ampm"));
	}
	
	@SuppressWarnings("unused")
	private WebElement endTimeAmPm(){
		return driver.findElement(By.name("end_time_ampm"));
	}
	
	private WebElement endTimeDropdown(){
		return driver.findElement(By.name("end_time_hours"));
	}
	
	private WebElement categoriesDropdown() {
		return driver.findElement(By.name("categories"));
	}

	private WebElement topicsDropdown() {
		return driver.findElement(By.name("topics"));
	}

	private WebElement eventLocationNameField(){
		return driver.findElement(By.name("location_name"));
	}
	
	private WebElement eventAdminNameField(){
		return driver.findElement(By.name("purchaser_name"));
	}
	
	private WebElement eventPhoneField(){
		return driver.findElement(By.name("purchaser_phone"));
	}
	
	private WebElement eventEmailField(){
		return driver.findElement(By.name("purchaser_email"));
	}
	
	private WebElement salesmanDropdown(){
		return driver.findElement(By.name("salesman"));
	}
	private WebElement saveEventButton(){
		return driver.findElement(By.id("saveButton"));
	}
	
	//deletion elements
	
	private WebElement deleteEventButton(){
		return driver.findElement(By.id("deleteButton"));
	}
	
	@SuppressWarnings("unused")
	private List<WebElement> qaListingTable(){
		return driver.findElement(By.id("main-content-col1")).findElements(By.tagName("tr"));
	}
	
	// --------------------------Helpers------------------------------------//
	
	public void setStartDate() {

		// determine date
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		date = c.getTime();
		c.add(Calendar.DATE, 1);
		boolean reset=false;
		

		//
		eventStartDatePicker().click();
		/*if(!datePickerBox().findElement(By.className("switch")).getText().contains(new SimpleDateFormat("MMMM").format(c.getTime()))){
			datePickerBox().findElement(By.className("switch")).click();
		}*/
		for (int i = 0; i < datePickerBox().findElements(By.tagName("td")).size(); i++) {		
			if (datePickerBox().findElements(By.tagName("td")).get(i).getText().equalsIgnoreCase("1")&& !reset) {
				interrupt.jsClick(datePickerBox().findElements(By.tagName("td")).get(i));
				reset = true;
			}
			if (datePickerBox().findElements(By.tagName("td")).get(i).getText().equalsIgnoreCase(Integer.toString(c.get(Calendar.DAY_OF_MONTH)))&&reset) {
				interrupt.jsClick(datePickerBox().findElements(By.tagName("td")).get(i));	
				interrupt.jsClick(datePickerBox().findElements(By.tagName("td")).get(i));	
				break;
			}
		}
	}

	public void setEndDate() {

		// determine date
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		// date = c.getTime();
		boolean reset=false;

		eventEndDatePicker().click();
		for (int i = 0; i < datePickerBox().findElements(By.tagName("td")).size(); i++) {		
			if (datePickerBox().findElements(By.tagName("td")).get(i).getText().equalsIgnoreCase("1")&& !reset) {
				interrupt.jsClick(datePickerBox().findElements(By.tagName("td")).get(i));
				reset = true;
			}
			if (datePickerBox().findElements(By.tagName("td")).get(i).getText().equalsIgnoreCase(Integer.toString(c.get(Calendar.DAY_OF_MONTH)))&&reset) {
				interrupt.jsClick(datePickerBox().findElements(By.tagName("td")).get(i));	
				interrupt.jsClick(datePickerBox().findElements(By.tagName("td")).get(i));	
				break;
			}
		}
	}
	
	/**
	 * Creates a new calendar listing using the data from the provided hashmap.
	 * @param data - hashmap from which to generate the listing info.
	 */
	public void newCalendarListing(HashMap<String,String> data){
		//remove all QA listings first
		while(deleteQaListings()){
			driver.navigate().refresh();
			SeleniumTest.logger.info("Removing QA listings..." + System.lineSeparator());
		}
		
		
		addCalendarListingButton().click();
		eventTitleField().sendKeys("QA listing");
		eventDetailsField().sendKeys(data.get("client"));
		eventSummaryField().sendKeys(data.get("market"));
		interrupt.scrollIntoView(eventDetailsField());
		setStartDate();
		setEndDate();
		
		//
		interrupt.scrollIntoView(eventStartDatePicker());
		startTimeAmPm().click();
     	
		//
		endTimeDropdown().click();
		endTimeDropdown().findElements(By.tagName("option")).get(4).click();
		startTimeDropdown().click();
		startTimeDropdown().findElements(By.tagName("option")).get(2).click();
		categoriesDropdown().findElements(By.tagName("option")).get(4).click();
		topicsDropdown().findElements(By.tagName("option")).get(2).click();
		eventLocationNameField().sendKeys(data.get("market"));
		eventAdminNameField().sendKeys("someone@mail.com");
		eventPhoneField().sendKeys(data.get("phone"));
		eventEmailField().sendKeys(data.get("username"));
		salesmanDropdown().findElements(By.tagName("option")).get(0).click();
		saveEventButton().click();
		
		Assert.assertTrue("Listing may not have been created!",wait.waitForPresenceOf(By.id("deleteButton")));
		SeleniumTest.logger.info("Calendar listing created..." + System.lineSeparator());
	}
	
	/**
	 * deletes the current QA listings
	 */
	public boolean deleteQaListings(){		
		String listing = "";
		boolean listingFound = false;
		
		//for each row in the table
		for(int i=0;i<calendarTable().findElements(By.tagName("tr")).size();i++){
			if(listingFound){
				i=0;
				listingFound=false;
			}
			
			//check to see if the row has a title
			if(calendarTable().findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).size()>2){				
				listing = calendarTable().findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(1).getText();
				
				//if row has a title, see if it equals 'QA listing'
				if(listing.contains("QA listing")){
					calendarTable().findElements(By.tagName("tr")).get(i).findElements(By.tagName("td")).get(2).findElement(By.tagName("a")).click();
					wait.waitForPresenceOf(By.id("deleteButton"));
					deleteEventButton().click();
					interrupt.checkAlert();
					SeleniumTest.logger.info("Removed" + listing + System.lineSeparator());
					Assert.assertTrue("Calendar lsitings page not found!",wait.waitForPresenceOf(By.linkText("ADD CALENDAR LISTING")));
					if(!wait.waitForPresenceOf(By.id("main-content-col1"))){
						SeleniumTest.logger.info("Event may not have been deleted..." + System.lineSeparator());
					}
					listingFound = true;
					return listingFound;					
				}
			}
		}
		return listingFound;
	}
}
