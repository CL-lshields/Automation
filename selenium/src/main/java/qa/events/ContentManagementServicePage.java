package qa.events;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.SeleniumTest;
import qa.utility.WaitTool;

import org.junit.Assert;


public class ContentManagementServicePage extends BasePage {
	WaitTool wait;
	String urlTracking="";

	public ContentManagementServicePage(WebDriver driver) {
		super(driver);
		wait = new WaitTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}
	
	public void setUrlTracking(String url){
		this.urlTracking = url;
	}
	
	public String getUrlTracking(){
		return this.urlTracking;
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

	private WebElement storiesButton() {
		return driver.findElement(By.id("nav-admin-stories"));
	}

	private WebElement storiesList() {
		return driver.findElement(By.id("content-list")).findElement(By.tagName("tbody"));
	}

	private WebElement marketSelect() {
		return driver.findElement(By.linkText("Bizjournals"));
	}

	private WebElement urlTracking(){
		return driver.findElement(By.tagName("input"));
	}
	// --------------------------Helpers-------------------------------------//

	/**
	 * Clicks the stories tab on the CMS page using the web element obtained
	 * in storiesButton()
	 */
	public void clickStoriesButton() {
		storiesButton().click();
	}

	/**
	 * Clicks an individual story under the stories tab. 
	 * Clicks the first story in the list if available.
	 * Attempts to account for JS alert announcing that story is
	 * already checked out by another user
	 */
	public void clickStory() {
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(storiesList().findElements(By.tagName("tr")).get(0).findElement(By.tagName("h3"))
					.findElement(By.tagName("a")))).click();;		
			
			//storiesList().findElements(By.tagName("tr")).get(0).findElement(By.tagName("h3"))
			//		.findElement(By.tagName("a")).click();
			
			if(urlTracking().isEnabled()){
				setUrlTracking(urlTracking().getAttribute("value"));
			}
		} catch (UnhandledAlertException f) {
			SeleniumTest.logger.severe("Alert may be interfering..."+f.getMessage()+System.lineSeparator());
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert data: " + alertText);
				alert.dismiss();
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
				SeleniumTest.logger.severe(e.getMessage()+System.lineSeparator());
			}
		}
	}

	/**
	 * Same functionality as clickStory(). Int i allows for
	 * specification of which story to click in the the list.
	 * 
	 * @param i - story to click
	 */
	public void clickStory(int i) {
		storiesList().findElements(By.tagName("tr")).get(i).findElement(By.tagName("h3")).findElement(By.tagName("a"))
				.click();
		SeleniumTest.logger.info("navigating to story "+i+System.lineSeparator());
	}

	/**
	 * Clicks on a specified market (city) in the header. This is needed 
	 * because this element is different from what is defined in the 
	 * AdminStagingHeader class.
	 * 
	 * @param market - specified city
	 */
	public void clickMarket(String market) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		marketSelect().click();
		driver.findElement(By.linkText(market)).click();
		wait.waitForVisibilityOf(driver.findElement(By.linkText(market)), 10);
		Assert.assertTrue(market + "is not the active city!", driver.findElement(By.linkText(market)).isDisplayed());
		SeleniumTest.logger.info("CMS is redirecting to market "+market+System.lineSeparator());
	}

}
