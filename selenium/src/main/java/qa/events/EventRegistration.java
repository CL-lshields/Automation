package qa.events;

import java.util.HashMap;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.SeleniumTest;
import qa.utility.WaitTool;

public class EventRegistration extends BasePage {
	WaitTool wait;
	
	public EventRegistration(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
	}	

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}
	
	//--------------------------Elements-------------------------------------//
	
	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}
	
	private WebElement firstName() {
		return driver.findElement(By.id("registration-primary_attendee-first_name"));
	}
	
	private WebElement lastName() {
		return driver.findElement(By.id("registration-primary_attendee-last_name"));
	}
	
	private WebElement jobTitle() {
		return driver.findElement(By.id("registration-primary_attendee-job_title_id"));
	}
	
	private WebElement emailAddress() {
		return driver.findElement(By.id("registration-primary_attendee-email"));
	}
	
	private WebElement contactPhone() {
		return driver.findElement(By.id("registration-primary_attendee-phone"));
	}
	
	private WebElement addressField() {
		return driver.findElement(By.id("registration-primary_attendee-address"));
	}
	
	private WebElement cityField() {
		return driver.findElement(By.id("registration-primary_attendee-city"));
	}
	
	private WebElement stateDropdown() {
		return driver.findElement(By.id("registration-primary_attendee-state"));
	}
	
	private WebElement zipCode() {
		return driver.findElement(By.id("registration-primary_attendee-zip"));
	}
	
	private WebElement referenceDropdown() {
		return driver.findElement(By.id("registration-primary_attendee-marketing_path"));
	}
	
	private WebElement promotionBox() {
		return driver.findElement(By.id("registration-promo_code"));
	}
	
	private WebElement completeRegistration() {
		return driver.findElement(By.id("submitBtn"));
	}

	//--------------------------Helpers-------------------------------------//
	
	public void fillFirstName(String name){
		firstName().sendKeys(name);
	}
	
	public void fillLastName(String name) {
		lastName().sendKeys(name);
	}
	
	public void fillJobTitle(int title) {
		jobTitle().click();
		jobTitle().findElements(By.tagName("option")).get(title).click();
	}
	
	public void fillEmailAddress(String email) {
		emailAddress().sendKeys(email);
	}
	
	public void fillContactPhone(String phone) {
		contactPhone().sendKeys(phone);
	}
	
	public void fillAddressField(String address) {
		addressField().sendKeys(address);
	}
	
	public void fillCityField(String city) {
		cityField().sendKeys(city);
	}
	
	public void fillStateDropdown(int state) {
		stateDropdown().click();
		stateDropdown().findElements(By.tagName("option")).get(state).click();
	}
	
	public void fillZipCode(String zip) {
		zipCode().sendKeys(zip);
	}
	
	public void fillReferenceDropdown(int reference) {
		referenceDropdown().click();		
		referenceDropdown().findElements(By.tagName("option")).get(reference).click();
		
	}
	
	public void fillPromotionBox(String promo) {
		promotionBox().sendKeys(promo);
	}
	
	public void fillCompleteRegistration() {
		completeRegistration().click();
	}
	
	/**Performs form validation while simultaneously registering for and event.
	 * 
	 * @param data - hashmap, usually provided by ExcelDriver
	 */
	public void formValidation(HashMap <String,String> data){
		fillFirstName(data.get("username"));
		fillCompleteRegistration();
		Assert.assertTrue("form submitted while incomplete!", driver.findElement(By.className("warning_error100")).isDisplayed());
		fillLastName(data.get("username"));
		fillCompleteRegistration();
		Assert.assertTrue("form submitted while incomplete!", driver.findElement(By.className("warning_error100")).isDisplayed());
		fillJobTitle(1);
		fillCompleteRegistration();
		Assert.assertTrue("form submitted while incomplete!", driver.findElement(By.className("warning_error100")).isDisplayed());
		fillEmailAddress(data.get("username"));
		fillCompleteRegistration();
		Assert.assertTrue("form submitted while incomplete!", driver.findElement(By.className("warning_error100")).isDisplayed());
		fillContactPhone(data.get("phone"));
		fillCompleteRegistration();
		Assert.assertTrue("form submitted while incomplete!", driver.findElement(By.className("warning_error100")).isDisplayed());
		fillAddressField(data.get("address"));
		fillCompleteRegistration();
		Assert.assertTrue("form submitted while incomplete!", driver.findElement(By.className("warning_error100")).isDisplayed());
		fillCityField(data.get("market"));
		fillCompleteRegistration();
		Assert.assertTrue("form submitted while incomplete!", driver.findElement(By.className("warning_error100")).isDisplayed());
		fillStateDropdown(1);
		fillCompleteRegistration();
		Assert.assertTrue("form submitted while incomplete!", driver.findElement(By.className("warning_error100")).isDisplayed());
		fillZipCode(data.get("zip"));
		fillCompleteRegistration();
		Assert.assertTrue("form submitted while incomplete!", driver.findElement(By.className("warning_error100")).isDisplayed());
		fillReferenceDropdown(1);
		
		if (!data.get("code").equalsIgnoreCase("n/a")){
			fillPromotionBox(data.get("code"));
			SeleniumTest.logger.info("promo code used: "+data.get("code")+System.lineSeparator());
		} else {
			fillPromotionBox("QATEST");
			SeleniumTest.logger.info("promo code used: QATEST..."+System.lineSeparator());
		}
		
		fillCompleteRegistration();	
		Assert.assertTrue("form did not submit!", driver.getCurrentUrl().contains("cart"));
		SeleniumTest.logger.info("registered for event in: "+data.get("market")+System.lineSeparator());
	}
	
	
	
	
}
