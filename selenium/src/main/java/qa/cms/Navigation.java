package qa.cms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import org.junit.Assert;
import qa.BasePage;
import qa.SeleniumTest;
import qa.utility.WaitTool;

public class Navigation extends BasePage {
	WaitTool wait;
	
	/**
	 * TOP ROW NAV
	 */
	//@FindBy(how = How.ID, using="nav__global")
	private WebElement userMenuNav(){
		Assert.assertTrue("could not find nav menu",wait.waitForPresenceOf(By.id("nav__global")));
		return driver.findElement(By.id("nav__global"));
	}
	
	@FindBy(how = How.CSS, using="li[data-qa=\"cms_user_menu\"]")
	private WebElement userMenuNavLink;
	
	@FindBy(how = How.LINK_TEXT, using="Log Out")
	private WebElement logoutLink;
	
	/**
	 * SECOND ROW NAV
	 */
	@FindBy(how = How.ID, using="nav-admin-stories")
	private WebElement storiesNavLink;
	
	@FindBy(how = How.ID, using="nav-admin-sections")
	private WebElement sectionsNavLink;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public Navigation(WebDriver driver) {
		super(driver);
		wait = new WaitTool(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	public Boolean pageIsValid() {
		//return adContainers.size() > 0;
		return true;
	}
	
	/**
	 * Use link text to navigate the cms user menu.
	 * 
	 * @param menu - text of the link to navigate
	 */
	public void navigateUserMenu(String menu){
		wait.waitForVisibilityOf(userMenuNav(), 10);
		wait.waitForPresenceOf(By.linkText(menu));
		try{
			driver.findElement(By.linkText(menu)).click();
		}catch(WebDriverException e){			
			String verify = driver.findElement(By.linkText(menu)).getAttribute("href");
			driver.navigate().to(verify);	
			SeleniumTest.logger.severe(e.getMessage()+System.lineSeparator());
			SeleniumTest.logger.severe("error caught, redirected using url path..."+System.lineSeparator());
		}
		SeleniumTest.logger.info("Navigated to "+menu+" in the cms..."+System.lineSeparator());
	}
	
	/**
	 * Use link text to navigate the cms user menu. This function overloads 
	 * navigateUserMenu and adds the ability to navigate through sub-menus. If
	 * there are no sub-menus, the base function should be used.
	 * 
	 * @param menu - text of the link to navigate
	 * @param submenu -  text of the sub-link to navigate
	 */
	public void navigateUserMenu(String menu, String submenu){
		wait.waitForVisibilityOf(userMenuNav(), 10);
		//Assert.assertTrue(menu + " not present!",wait.waitForPresenceOf(By.linkText(menu)));		
		driver.findElement(By.linkText(menu)).click();
		//Assert.assertTrue(submenu + " not present!",wait.waitForPresenceOf(By.linkText(submenu)));
		driver.findElement(By.className("navbar-inner")).findElement(By.linkText(submenu)).click();
		SeleniumTest.logger.info("Navigated to "+menu+" in the cms..."+System.lineSeparator());
	}
	
	/**
	 * Navigate to the specified market in the cms header.
	 * @param market - market to navigate to.
	 */
	public void navigateMarketSelect(String market){
		for(int i = 0;i<userMenuNav().findElements(By.tagName("li")).size();i++){
			if(userMenuNav().findElements(By.tagName("li")).get(i).getAttribute("data-qa")!=null
				&&	userMenuNav().findElements(By.tagName("li")).get(i).getAttribute("data-qa").equals("cms_market_select")){
				userMenuNav().findElements(By.tagName("li")).get(i).click();
				userMenuNav().findElements(By.tagName("li")).get(i).findElement(By.linkText(market)).click();
				SeleniumTest.logger.info("Navigated to "+market+" in the cms..."+System.lineSeparator());
				wait.sleep(5000);
				Assert.assertTrue("Incorrect Market!",driver.findElement(By.className("market-selector-button")).getText().contains(market));
				break;
			}
		}
	}
	
	/**
	 * Click 'Stories' nav link to go to the Stories home page
	 * 
	 * @return POM for the Stories Home Page
	 */
	public StoriesHomePage clickStoriesNavLink() {
		storiesNavLink.click();
		
		return new StoriesHomePage(driver);			
	}
	
	/**
	 * Click 'Sections' nav link to go to the Sections home page
	 * 
	 * @return POM for the Sections Home Page
	 */
	public SectionsHomePage clickSectionsNavLink() {
		sectionsNavLink.click();
		
		return new SectionsHomePage(driver);
	}
	
	/**
	 * Open user menu to access user functions
	 */
	private void openUserMenu() {
		userMenuNavLink.click();
	}
	
	/**
	 * Get the text inside the user menu link
	 * 
	 * @return String currently representing the username of the logged in user
	 */
	public String getUserMenuText() {
		return userMenuNavLink.getText();
	}
	
	/**
	 * Logout of the CMS
	 * 
	 * @return POM for the Login page
	 */
	public LoginPage logout() {
		openUserMenu();
		logoutLink.click();
		
		return new LoginPage(driver);
	}
	
	
	

}
