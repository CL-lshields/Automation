package qa.bizjournals;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.SeleniumTest;
import qa.events.AdminStagingHeader;
import qa.utility.InterruptTool;
import qa.utility.MarketList;
import qa.utility.WaitTool;
import qa.utility.bizjdb.DatabaseTool;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

public class BizJournalsHeader extends qa.events.BasePage {
	WaitTool wait;
	InterruptTool interrupt;
	DatabaseTool databaseTool;

	public BizJournalsHeader(WebDriver driver) {
		super(driver);
		
		// TODO Auto-generated constructor stub
		wait = new WaitTool(driver);
		interrupt = new InterruptTool(driver);			
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}
	
	//navigate to account page
	public void toAccount(){
		driver.navigate().to(driver.getCurrentUrl()+"account/bizjournals?r=%2Faccount");
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement pageHeaderBar() {
		return driver.findElement(By.className("header"));
		//return driver.findElement(By.xpath("/html/body/header"));
	}

	@SuppressWarnings("unused")
	private WebElement headerMenuButton() {
		return driver.findElement(By.xpath("//button"));
		//return driver.findElement(By.xpath("//*[text()='Menu']"));
	}

	private WebElement headerHomeIcon() {
		return driver.findElement(By.className("header__logo"));
	}

	@SuppressWarnings("unused")
	private WebElement headerSearch() {
		return pageHeaderBar().findElements(By.tagName("li")).get(3);
	}

	@SuppressWarnings("unused")
	private WebElement userWelcome() {
		return pageHeaderBar().findElements(By.tagName("li")).get(4).findElement(By.className("menu-container"))
				.findElement(By.className("menu-header"));
	}

	private WebElement headerSignIn() {
		wait.waitForPresenceOf(By.className("banner"));
		driver.findElement(By.className("banner")).findElement(By.className("dropdown__trigger")).click();
		wait.waitForPresenceOf(By.className("dropdown__menu"));
		wait.waitForVisibilityOf(driver.findElement(By.className("js-clear-user-state")), 15);
		return driver.findElement(By.className("js-clear-user-state"));
		//return driver.findElement(By.xpath("//*[text()='Sign In']"));
		//return pageHeaderBar().findElements(By.tagName("li")).get(4);
	}
	
	public WebElement accountPageButton(){
		wait.waitForPresenceOf(By.id("accountMenu"));
		driver.findElement(By.id("accountMenu")).click();
		wait.waitForPresenceOf(By.linkText("Your Account"));
		return driver.findElement(By.linkText("Your Account"));
	}
	
	public WebElement newslettersButton(){
		wait.waitForPresenceOf(By.id("accountMenu"));
		driver.findElement(By.id("accountMenu")).click();
		wait.waitForPresenceOf(By.linkText("Newsletters"));
		return driver.findElement(By.linkText("Newsletters"));
	}

	private WebElement headerLoggedIn() {
		return driver.findElement(By.id("mainBody")).findElement(By.tagName("h1"));
	}
	
	private List<WebElement> bannerAndNavigation(){
		return driver.findElement(By.className("banner-and-nav")).findElements(By.tagName("li"));
	}
	
	private List<WebElement> cityList(){
		return driver.findElement(By.className("select-city__link-list")).findElements(By.tagName("a"));
	}
	// --------------------------Helpers-------------------------------------//

	/**
	 * Select market in the header bar of Bizjournals site
	 * 
	 * @param market
	 *            - specified city, usually provided by a hashmap found in an
	 *            instance of ExcelDriver.
	 */
	public void marketSelect(String market) {
		if(market.equals("random")){	
			if(AdminStagingHeader.randomMarket!=null){
				market = AdminStagingHeader.randomMarket;
			}else{
				MarketList marketList = new MarketList();
				marketList.loadMarkets();
				Map<String, Object> marketContainer = marketList.getRandomMarket();
				AdminStagingHeader.randomMarket=(String) marketContainer.get("city");
				SeleniumTest.logger.info((String) marketContainer.get("city")+" is the random market to be tested..." + System.lineSeparator());
				market = AdminStagingHeader.randomMarket;
			}			
		}
		//headerMenuButton().click();
		wait.waitForJs(driver);
		wait.waitForPresenceOf(By.className("select-city"), 20);
		driver.findElement(By.className("select-city")).click();
		wait.waitForPresenceOf(By.className("select-city__link-list"));
		for(int i=0;i<cityList().size();i++){
			if(cityList().get(i).getText().contains(market)){
				String verify = cityList().get(i).getAttribute("href");
				driver.get(verify);
				Assert.assertTrue("incorrect market selected " + driver.getCurrentUrl(), driver.getCurrentUrl().contains(verify));
				SeleniumTest.logger.info("selected " + market + " in bizJournals header..." + System.lineSeparator());
				break;
			}
		}		
	}
	
	/**
	 * Navigate the bizjournals header menu using menu and submenu strings
	 * 
	 * @param menu
	 *            - name of the menu item in the header menu bar
	 * @param submenu
	 *            - name of the submenu item in the header menu bar.
	 */
	public void navigateHeader(String menu, String submenu) {		
		for(int i=0;i<bannerAndNavigation().size();i++){
			if(bannerAndNavigation().get(i).getText().equals(menu)){
				bannerAndNavigation().get(i).click();
			}
		}
		Assert.assertTrue("Couldnt find " + submenu + " link in the menu bar",
				wait.waitForPresenceOf(By.linkText(submenu)));	
		String verify;
		try{		
			verify = driver.findElement(By.linkText(submenu)).getAttribute("href");
		}catch(StaleElementReferenceException e){
			if(!wait.waitForPresenceOf(By.linkText(submenu))){
				SeleniumTest.logger.info("A script maybe affecting the performance of the homepage..." + System.lineSeparator());
				Assert.fail("Page interruption!");				
			}
			verify = driver.findElement(By.linkText(submenu)).getAttribute("href");
		}		
		driver.navigate().to(verify);
		if(!driver.getCurrentUrl().equals(verify)){
			driver.get(verify);
		}
		Assert.assertTrue("Header not navigate to "+verify,driver.getCurrentUrl().contains(verify));
		SeleniumTest.logger.info("Succesfully navigated BizJournals header to..."+ driver.getCurrentUrl() + System.lineSeparator());
		//interrupt.checkPagePerformance();
	}

	/**
	 * Login to Bizjounrals site from the header. This is a modified version of the 
	 * SignInTest by @author ahagge.
	 * 
	 * @param email - username
	 * @param password - password
	 */
	public void loginToBizJournals(String email, String password) {
		Navigation nav = new Navigation(driver);
		logger.info("Opening Sign In page");
		SignInPage loginPage = nav.clickToSignIn();
		
		logger.info("Signing in with valid credentials for ("+email+")");
		loginPage.signIn(email, password);
		
		// there is a delay in the nav registering that a user is logged in, so we have to wait :(
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nav = new Navigation(driver);
		
		Assert.assertTrue("User not logged in.", nav.isUserLoggedIn());	
	}

	/**
	 * Navigate home using the BizJournals logo icon in the page header
	 */
	public void goHome() {
		driver.navigate().to(headerHomeIcon().findElement(By.tagName("a")).getAttribute("href"));
		interrupt.checkPagePerformance();
	}
	
	
	/**
	 * This is the old primary login method. This should only be attempted if the
	 * loginToBizJournals() method is unsuccesful, though this method may not fare
	 * any better.
	 * 
	 * @param email - username
	 * @param password - password
	 */
	public void alternativeLogin(String email, String password){		
		interrupt.handleInterstitial();
		wait.waitForJs(driver);
		//wait.waitForVisibilityOf(headerSignIn(), 30);		
		headerSignIn().click();			
		wait.waitForPresenceOf(By.id("emailAddress"));
		Assert.assertTrue("Login screen did not appear", driver.getCurrentUrl().contains("login"));
		//interrupt.checkPagePerformance();
		try{
			driver.findElement(By.id("emailAddress")).sendKeys(email);
			wait.sleep(3000);
			Assert.assertTrue("Incorrect email",driver.findElement(By.id("emailAddress")).getAttribute("value").equals(email));			
			SeleniumTest.logger.info("email submitted..." + System.lineSeparator());
			interrupt.scrollIntoView(driver.findElement(By.id("emailAddress")));
			interrupt.jsClick(driver.findElement(By.id("password")));			
			driver.findElement(By.id("password")).clear();
			wait.sleep(3000);
			interrupt.sendKeysJs(password, driver.findElement(By.id("password")));			
			//driver.findElement(By.id("password")).sendKeys(password);
			wait.sleep(3000);
			Assert.assertTrue("Incorrect password" + driver.findElement(By.id("password")).getAttribute("value"),driver.findElement(By.id("password")).getAttribute("value").equals(password));
			SeleniumTest.logger.info("password submitted..." + System.lineSeparator());
			wait.sleep(5000);	
			driver.findElement(By.id("submit")).click();
			SeleniumTest.logger.info("form submitted..." + System.lineSeparator());
			wait.sleep(5000);
			//toAccount();
			
			wait.waitForJs(driver);
			wait.waitForPresenceOf(By.className("item--featured"));
			accountPageButton().click();
	 		Assert.assertTrue("Not properly logged in",headerLoggedIn().getText().toLowerCase().contains("welcome"));
	 		SeleniumTest.logger.info("Account verified..." + System.lineSeparator());
			driver.navigate().back();
			wait.waitForPresenceOf(By.id("accountMenu"));
			Assert.assertTrue("Page Not loaded after login!",
					driver.findElement(By.id("accountMenu")).getText().contains("WELCOME"));
			SeleniumTest.logger.info("logged into BizJournals..." + System.lineSeparator());
			
		}catch(UnhandledAlertException uae){
			//log error and retry logging in
			SeleniumTest.logger.warning(uae.getMessage() + System.lineSeparator());
			//Alert alert = driver.switchTo().alert();
			//alert.accept();
			//driver.findElement(By.id("submit")).click();
		}	
	}

}
