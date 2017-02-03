package qa.magento;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.junit.Assert;
import qa.SeleniumTest;
import qa.events.BasePage;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class MagentoCheckoutComponent extends BasePage {
	WaitTool wait;
	InterruptTool interrupt;

	public MagentoCheckoutComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}	

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.name("title"));
	}
	
	private WebElement firstNameField() {
		return driver.findElement(By.name("firstname"));
	}
	
	private WebElement lastNameField() {
		return driver.findElement(By.name("lastname"));
	}
	
	private WebElement streetAddressField() {
		return driver.findElement(By.name("street[0]"));
	}
	
	private WebElement cityField() {
		return driver.findElement(By.name("city"));
	}
	
	private WebElement stateDropdown() {
		return driver.findElement(By.name("region_id"));
	}
	
	private List<WebElement> stateDropdownOptions() {
		return driver.findElement(By.name("region_id")).findElements(By.tagName("option"));
	}
	
	private WebElement zipField() {
		return driver.findElement(By.name("postcode"));
	}
	
	private WebElement phoneNumberField() {
		return driver.findElement(By.name("telephone"));
	}
	
	private WebElement emailField() {
		return driver.findElement(By.id("customer-email"));
	}
	
	private WebElement nextButton() {
		return driver.findElement(By.id("shipping-method-buttons-container")).findElement(By.tagName("button"));
	}
	
	private WebElement creditCardcheckBox(){
		return driver.findElement(By.id("checkout-payment-method-load")).findElement(By.id("braintree"));
	}
	
	//credit card form
	private WebElement creditCardFrame() {
		return driver.findElement(By.id("braintree-hosted-field-number"));		
	}
	
	private WebElement creditCardField() {
		return driver.findElement(By.id("credit-card-number"));		
	}
	
	private WebElement expirationMonthFrame() {
		return driver.findElement(By.id("braintree-hosted-field-expirationMonth"));
	}
	
	private WebElement expirationMonthField() {
		return driver.findElement(By.id("expiration-month"));
	}
	
	private WebElement expirationYearFrame() {
		return driver.findElement(By.id("braintree-hosted-field-expirationYear"));
	}
	
	private WebElement expirationYearField() {
		return driver.findElement(By.id("expiration-year"));
	}
	
	private WebElement cvvFrame() {
		return driver.findElement(By.id("braintree-hosted-field-cvv"));
	}
	
	private WebElement cvvField() {
		return driver.findElement(By.id("cvv"));
	}

	private WebElement placeOrderButton(){
		for(int i=0;i<driver.findElement(By.id("payment")).findElements(By.tagName("button")).size();i++){
			if(driver.findElement(By.id("payment")).findElements(By.tagName("button")).get(i).getAttribute("title").equals("Place Order")){
				return driver.findElement(By.id("payment")).findElements(By.tagName("button")).get(i);
			}
		}
		return null;
	}
	// --------------------------Helpers-------------------------------------//

	public void submitCheckoutForm(HashMap<String,String> data){
		//driver.get("http://lshields.magento.bizj-internal.com/cart/product/add/sku/ci-52-2341-2591?paid=20352602&u=uvO35erP5bd0iDn4mvHGkw0420a112&mcode=cincinnati&path=%2Fcincinnati%2Fnews%2F2016%2F08%2F12%2F");
		wait.waitForPresenceOf(By.name("firstname"));
		firstNameField().sendKeys(data.get("username"));
		lastNameField().sendKeys(data.get("market"));
		streetAddressField().sendKeys(data.get("address"));
		cityField().sendKeys(data.get("market"));
		//stateDropdown().click();
		stateDropdownOptions().get(5).click();
		zipField().sendKeys(data.get("zip").replace(".0", ""));
		phoneNumberField().sendKeys(data.get("phone"));
		emailField().sendKeys(data.get("username"));
		nextButton().click();
		
		//payment info		
		//creditCardcheckBox().click();
		Assert.assertTrue("Payment options not present", wait.waitForPresenceOf(By.className("credit-card-types")));
		driver.switchTo().frame(creditCardFrame());
		creditCardField().sendKeys(data.get("credit"));	
		String[] exp = data.get("expiration").split("\\/");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(expirationMonthFrame());
		expirationMonthField().sendKeys(exp[0]);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(expirationYearFrame());
		expirationYearField().sendKeys(exp[1]);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(cvvFrame());
		cvvField().sendKeys(data.get("cvv"));
		driver.switchTo().defaultContent();
		interrupt.jsClick(placeOrderButton());		
		if(data.get("expected").equals("true")){
			wait.waitForPresenceOf(By.className("checkout-success"));
			Assert.assertTrue("Checkout was not succesful", driver.findElement(By.className("checkout-success")).isDisplayed());
			SeleniumTest.logger.info("completed chekcout form..." + System.lineSeparator());
		}else{			
			Assert.assertFalse("Checkout was not succesful", wait.waitForPresenceOf(By.className("checkout-success")));
			SeleniumTest.logger.info("completed chekcout form..." + System.lineSeparator());
		}
	}
}
