package qa.events;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.SeleniumTest;
import qa.utility.MarketList;
import qa.utility.WaitTool;


public class AdminStagingHeader extends BasePage {
	WaitTool wait;
	public static String randomMarket;
	
	public AdminStagingHeader(WebDriver driver) {
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
	
	public WebElement myPageSelect(){
		return driver.findElement(By.id("menu__main"));
	}
	
	public WebElement myMarketSelect(){
		return driver.findElement(By.id("market-label"));
	}
	
	public WebElement mySupportSelect(){
		return driver.findElement(By.className("nav navbar-nav pull-right hidden-xs"));
	}
	
	public WebElement myUserProfile(){
		return driver.findElement(By.id("userOptions"));
	}
	
	
	//--------------------------Helpers-------------------------------------//
	
	/**
	 * Selects the desired page in the admin staging header navigation menu.
	 * @param page - the name of the desired link to click in the navigation menu.
	 */
	public void selectMyPage(String page){
		myPageSelect().click();
		driver.findElement(By.linkText(page)).click();
		SeleniumTest.logger.info("navigated to "+page+" in admin stagin header..."+System.lineSeparator());
	}
	
	/**
	 *  Selects the desired page and subpage in the admin staging header navigation menu.
	 * 
	 * @param page - the name of the desired link to click in the navigation menu.
	 * @param subPage -  the name of the desired subpage link to click in the navigation menu.
	 */
	public void selectMyPage(String page, String subPage){
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(myPageSelect()));
		myPageSelect().click();
		driver.findElement(By.linkText(page)).click();	
		driver.findElement(By.linkText(subPage)).click();	
		SeleniumTest.logger.info("navigated to "+page+" > "+subPage+" in admin staging header..."+System.lineSeparator());
	}
	
	/**
	 * Selects the specified market in the admin staging header
	 * @param market - the specified city to work in. usually provided in the hashmap
	 * an ExcelDriver instance.
	 */
	
	public void selectMyMarket(String market){		
		if(market.equals("random")){			
			MarketList marketList = new MarketList();
			marketList.loadMarkets();
			Map<String, Object> marketContainer = marketList.getRandomMarket();
			randomMarket = (String) marketContainer.get("city");	
			market = randomMarket;
		}
		myMarketSelect().click();
		driver.findElement(By.linkText(market)).click();	
		Boolean element = (new WebDriverWait(driver, 10))
				   .until(ExpectedConditions.textToBePresentInElementLocated(By.id("market-label"), market));		
		Assert.assertTrue(market+" is not the active city!", element); 	
		SeleniumTest.logger.info("selected "+market+" in admin staging header..."+System.lineSeparator());
	}

	/**
	 * Navigates to the support page of Admin staging enviornment.	
	 * @param support - specified submenu item of support menu
	 */
	public void selectMySupport(String support){
		mySupportSelect().click();
		mySupportSelect().findElement(By.tagName("a")).findElement(By.linkText(support)).click();
		SeleniumTest.logger.info("selected supposrt page "+support+"..."+System.lineSeparator());
	}

	/**
	 * Navigates to the profile page of Admin staging enviornment.
	 * @param option
	 */
	public void selectMyProfile(String option){
		myUserProfile().click();
		myUserProfile().findElement(By.tagName("a")).findElement(By.linkText(option)).click();
		SeleniumTest.logger.info("selected profile option "+option+" in admin staging..."+System.lineSeparator());
	}
	
	
	
}
