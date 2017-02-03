package qa.events;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.SeleniumTest;
import qa.utility.WaitTool;

public class AdminCommerceComponent extends BasePage {
	WaitTool wait;

	public AdminCommerceComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
	}
	
	public void toPage() {
		// TODO Auto-generated method stub		
		driver.navigate().to("http://admin.staging.bizjournals.com/");
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement productBundles() {
		return driver.findElement(By.id("main")).findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
	}

	private WebElement promoCodes() {
		return driver.findElement(By.id("main")).findElement(By.tagName("ul")).findElements(By.tagName("li")).get(2);
	}

	private WebElement searchLink() {
		return driver.findElement(By.linkText("Search"));
	}

	private WebElement searchButton() {
		return driver.findElement(By.name("submit"));
	}

	private WebElement isActive() {
		return driver.findElement(By.id("is_active"));
	}

	private WebElement bundleMarketCode() {
		return driver.findElement(By.id("market_code"));
	}
	
	private WebElement bundleEventId(){
		return driver.findElement(By.id("assoc_products"));
	}
	
	private WebElement registerTable(){
		return driver.findElement(By.id("register"))
				.findElement(By.className("module"))
				.findElement(By.className("module__body"));				
	}
	
	private WebElement promoCodeSearchBox(){
		return driver.findElement(By.id("search_term"));
	}
	
	private WebElement promoCodeSearchButton(){
		return driver.findElement(By.name("submit"));
	}
	
	private WebElement promoCodeTable(){
		return driver.findElement(By.id("promocode_list"));
	}
	
	//register promo codes
	private WebElement promoCodeNameLabel(){
		return driver.findElement(By.id("promo_code_name"));
	}
	
	private WebElement promoCodeLabel(){
		return driver.findElement(By.id("token"));
	}
	
	private WebElement promoCodePercentageField(){
		return driver.findElement(By.id("percentage_rebate"));
	}
	
	private WebElement promoCodeQuantityField(){
		return driver.findElement(By.id("quantity_limit"));
	}
	
	private WebElement promoCodeNationalField(){
		return driver.findElement(By.id("is_national"));
	}
	
	private WebElement promoCodeActiveField(){
		return driver.findElement(By.id("is_active"));
	}
	
	private WebElement datePickerCalendar() {
		return driver.findElement(By.className("ui-datepicker-calendar"));
	}
	
	private WebElement promoCodeStartField(){
		return driver.findElement(By.id("start_date"));
	}
	
	private WebElement promoCodeEndField(){
		return driver.findElement(By.id("end_date"));
	}
	
	private WebElement promoCodeUsesField(){
		return driver.findElement(By.id("uses_left"));
	}	
	
	private WebElement promoCodeEventCheckBox(){
		return driver.findElement(By.id("product_type_ids-4"));
	}
	
	private WebElement promoCodeSaveButton(){
		return driver.findElement(By.name("save"));
	}
	
	
	// --------------------------Helpers------------------------------------//

	/**
	 * Select a market in which to search for product bundles. The string passed will 
	 * usually come from the hashmap in an instance of ExcelDriver.
	 * 
	 * @param market - city in which to search for product bundles
	 */
	public void searchProductBundles(String market) {
		productBundles().click();
		searchLink().click();
		bundleMarketCode().click();
		for(int i=0;i<bundleMarketCode().findElements(By.tagName("option")).size();i++){
			if(bundleMarketCode().findElements(By.tagName("option")).get(i).getText().equalsIgnoreCase(market)){
				bundleMarketCode().findElements(By.tagName("option")).get(i).click();
				break;
			}
		}		
		isActive().click();
		for(int i=0;i<isActive().findElements(By.tagName("option")).size();i++){
			if(isActive().findElements(By.tagName("option")).get(i).getText().equalsIgnoreCase("yes")){
				isActive().findElements(By.tagName("option")).get(i).click();
				break;
			}
		}			
		searchButton().click();
		Assert.assertTrue("Could not navigate to bundles!", driver.findElement(By.id("bundle_list")).isDisplayed());
		SeleniumTest.logger.info("market "+market+" for product bundles..."+System.lineSeparator());
	}

	//choose to search for promo codes
	public void searchPromoCodes() {
		promoCodes().click();
		searchLink().click();
	}
	
	/**
	 * Selects the first bundle in the table populated after searching for product bundles.
	 */
	public void selectBundle(){
		Assume.assumeTrue("No Bundles Exist in current market!",wait.waitForPresenceOf(By.tagName("tbody")));		
		String urlPath = driver.findElement(By.id("bundle_list")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(1)
		.findElements(By.tagName("td")).get(1).findElement(By.tagName("a")).getAttribute("href");
		//toPage();
		//driver.navigate().to(driver.getCurrentUrl().concat(urlPath));
		driver.navigate().to(urlPath);
		SeleniumTest.logger.info("first product bundle selected..."+System.lineSeparator());
	}
	
	/**
	 * @overload - Selects specific bundle in the table populated after searching for product bundles.
	 * @param i - item to select
	 */
	public void selectBundle(int i){
		String urlPath = driver.findElement(By.id("bundle_list")).findElements(By.tagName("tr")).get(i)
				.findElements(By.tagName("td")).get(1).findElement(By.tagName("a")).getAttribute("href");
				driver.navigate().to(driver.getCurrentUrl().concat(urlPath));
				SeleniumTest.logger.info("product bundle "+i+" selected..."+System.lineSeparator());
	}
	
	/**
	 * Verify that the event associated with the bundle is displaying bundles properly.
	 */
	public void verifyBundleEvent(){
		String productName, eventId, description, urlPath;
		urlPath = bundleEventId().findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(2).findElement(By.tagName("a")).getAttribute("href");
		
		//Debug
		//System.out.println(urlPath);
		
		driver.navigate().to(urlPath);
		productName = driver.findElement(By.id("price")).getAttribute("value");
		eventId = driver.findElement(By.id("parameters-event_id")).getAttribute("value");
		description = driver.findElement(By.id("parameters-description")).getAttribute("value");
		switchTab(1);
		
		//TODO - this line will have to be remedied to handle any events market, instead of only charlotte.
		driver.navigate().to("http://staging.bizjournals.com/charlotte/event/" + eventId);
		//////////////////////////this is currently a bug ^^///////////////////////////////////////////
		
		for(int i=0;i<registerTable().findElements(By.className("ticket-type ")).size();i++){
			if(registerTable().findElements(By.className("ticket-type ")).get(i)
					.findElement(By.className("ticket-type__price")).getText()
					.equalsIgnoreCase(productName)){
				
			boolean temp=registerTable().findElements(By.className("ticket-type ")).get(i)
				.findElement(By.className("ticket-type__body"))
				.findElement(By.className("row"))
				.findElement(By.tagName("p")).getText().equalsIgnoreCase(description);
				Assert.assertTrue("Incorrect bundle!",temp);
				break;
			}
				
		}
		SeleniumTest.logger.info("bundle event"+eventId+" verified..."+System.lineSeparator());
		
	}
	
	public void setStartDate() {
		Calendar c = Calendar.getInstance();		
		Date date = new Date();
		c.setTime(date);
		date = c.getTime();
		//c.add(Calendar.DATE, -2);
		promoCodeStartField().click();
		Assert.assertTrue("Date picker did not appear!", datePickerCalendar().isDisplayed());
		datePickerCalendar().findElements(By.tagName("a")).get(c.get(Calendar.DAY_OF_MONTH)).click();
	}

	public void setEndDate() {
		Calendar c = Calendar.getInstance();		
		Date date = new Date();
		c.setTime(date);		
		date = c.getTime();
		//c.add(Calendar.DATE, -1);
		wait.waitForElementToBeClickable(promoCodeEndField(), 5);
		promoCodeEndField().click();
		Assert.assertTrue("Date picker did not appear!", datePickerCalendar().isDisplayed());
		try{
		datePickerCalendar().findElements(By.tagName("a")).get(c.get(Calendar.DAY_OF_MONTH + 2)).click();
		}catch(IndexOutOfBoundsException ex){				
			driver.findElement(By.className("ui-datepicker-next")).click();
			datePickerCalendar().findElements(By.tagName("a")).get(c.get(Calendar.DAY_OF_MONTH)).click();			
		}
	}
	
	public void createPromoCode(HashMap<String, String> data){
		 driver.findElement(By.linkText("Search")).click();;
		 promoCodeSearchBox().sendKeys("QATEST");
		 promoCodeSearchButton().click();
		 Assert.assertTrue("Product code table missing!",wait.waitForPresenceOf(By.id("promocode_list")));
		 if(promoCodeTable().findElements(By.tagName("tr")).size()>1){
			 SeleniumTest.logger.info("Promo code already exists..."+System.lineSeparator());
		 }else{
			 promoCodes().click();
			 driver.findElement(By.linkText("Create new")).click();;
			 promoCodeNameLabel().sendKeys("QATEST");
			 promoCodeLabel().sendKeys("QATEST");
			 promoCodeNationalField().click();
			 promoCodeActiveField().click();
			 setStartDate();
			 setEndDate();
			 promoCodePercentageField().sendKeys(data.get("promo"));
			 promoCodeQuantityField().sendKeys("-1");
			 promoCodeUsesField().sendKeys("-1");
			 promoCodeEventCheckBox().click();
			 promoCodeSaveButton().click();			
		 }
	}

}
