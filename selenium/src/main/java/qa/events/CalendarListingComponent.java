package qa.events;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class CalendarListingComponent extends BasePage {
	WaitTool wait;
	InterruptTool interrupt;

	public CalendarListingComponent(WebDriver driver) {
		super(driver);
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement eventNameField() {
		return driver.findElement(By.id("title"));
	}

	private WebElement summaryField() {
		return driver.findElement(By.id("summary"));
	}

	private WebElement startDateField() {
		return driver.findElement(By.id("start_date"));
	}

	private WebElement endDateField() {
		return driver.findElement(By.id("end_date"));
	}

	private List<WebElement> startTimeHour() {
		return driver.findElement(By.id("start_time_hours")).findElements(By.tagName("option"));
	}

	private List<WebElement> startTimeMinute() {
		return driver.findElement(By.id("start_time_minutes")).findElements(By.tagName("option"));
	}

	private List<WebElement> startTimeAmPm() {
		return driver.findElement(By.id("start_time_ampm")).findElements(By.tagName("option"));
	}

	private List<WebElement> endTimeHour() {
		return driver.findElement(By.id("end_time_hours")).findElements(By.tagName("option"));
	}

	private List<WebElement> endTimeMinute() {
		return driver.findElement(By.id("end_time_minutes")).findElements(By.tagName("option"));
	}

	private List<WebElement> endTimeAmPm() {
		return driver.findElement(By.id("end_time_ampm")).findElements(By.tagName("option"));
	}

	private List<WebElement> calendarPicker(){
		return driver.findElement(By.className("ui-datepicker-calendar")).findElements(By.tagName("a"));
	}
	
	private WebElement calendarPickerNextMonth(){
		return driver.findElement(By.className("ui-icon-circle-triangle-e"));
	}	
	
	private WebElement descriptionField() {
		return driver.findElement(By.className("redactor_editor"));
	}

	private List<WebElement> cityDropownField() {
		return driver.findElement(By.id("market")).findElements(By.tagName("option"));
	}

	private List<WebElement> categoryDropownField() {
		return driver.findElement(By.id("category")).findElements(By.tagName("option"));
	}

	private List<WebElement> topicDropownField() {
		return driver.findElement(By.id("topic")).findElements(By.tagName("option"));
	}
	
	private WebElement locationNameField(){
		return driver.findElement(By.id("location_name"));
	}
	
	private WebElement listingNameField(){
		return driver.findElement(By.id("purchaser_name"));
	}

	private WebElement listingMailField(){
		return driver.findElement(By.id("purchaser_email"));
	}
	
	private WebElement listingPhoneField(){
		return driver.findElement(By.id("purchaser_phone"));
	}
	
	private WebElement listingPreviewButton(){
		return driver.findElement(By.id("preview"));
	}
	
	private WebElement nextButton(){
		return driver.findElement(By.id("cal_listing_submission_form")).findElement(By.className("form__action"))
		.findElement(By.className("ps-btn-next"));
	}

	// --------------------------Helpers-------------------------------------//

	private WebElement pickFromCalendar(List<WebElement> list){
		// determine date
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		date = c.getTime();
		c.add(Calendar.DATE, -1);
		
		try{
			return list.get(c.get(Calendar.DAY_OF_MONTH));
		}catch(IndexOutOfBoundsException ex){
			calendarPickerNextMonth().click();
			list = calendarPicker();
			return list.get(1);
		}		
	}
	
	private WebElement pickFromCalendar(List<WebElement> list, int days){
		// determine date
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		date = c.getTime();
		c.add(Calendar.DATE, days);
		
		try{
			return list.get(c.get(Calendar.DAY_OF_MONTH));
		}catch(IndexOutOfBoundsException ex){
			calendarPickerNextMonth().click();
			list = calendarPicker();
			return list.get(1);
		}		
	}
	
	public void createListing(HashMap<String, String> data) {		
		//create calendar listing
		eventNameField().sendKeys(data.get("market"));
		summaryField().sendKeys(data.get("market"));
		descriptionField().sendKeys(data.get("client"));
		startDateField().click();
		pickFromCalendar(calendarPicker()).click();
		endDateField().click();
		pickFromCalendar(calendarPicker(),1).click();
		startTimeHour().get(1).click();
		endTimeHour().get(1).click();
		startTimeMinute().get(1);
		endTimeMinute().get(1);
		startTimeAmPm().get(1);
		endTimeAmPm().get(1);
		cityDropownField().get(1).click();
		categoryDropownField().get(1).click();
		topicDropownField().get(1).click();
		nextButton().click();
		
		//page 2
		locationNameField().sendKeys(data.get("market"));
		interrupt.jsClick(nextButton());
		
		//page 3
		interrupt.jsClick(nextButton());
		
		//page4
		listingNameField().sendKeys(data.get("market"));
		listingMailField().sendKeys(data.get("username"));
		listingPhoneField().sendKeys(data.get("phone"));
		interrupt.jsClick(listingPreviewButton());
		
		//verfiy preview
        driver.switchTo().frame(driver.findElement(By.id("modal--preview")).findElement(By.tagName("iframe")));        
		Assert.assertTrue("Preview not displayed properly", wait.waitForPresenceOf(By.className("d-hero__body")));
	}
}
