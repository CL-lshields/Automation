package qa.bizjournals.hotd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.junit.Assert;

import qa.SeleniumTest;
import qa.events.BasePage;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public class HodComponent extends BasePage{
	WaitTool wait;
	InterruptTool interrupt;
	
	public HodComponent(WebDriver driver) {
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
	
	@SuppressWarnings("unused")
	private WebElement hodListings(){
		return driver.findElement(By.id("hodListings"));
	}
	
	//--------------------------Helpers-------------------------------------//
	
	public void areListingsDisplayed(){
		Assert.assertTrue("H.O.D Listings not displayed!", wait.waitForPresenceOf(By.id("hodListings")));
		interrupt.checkPagePerformance();
		SeleniumTest.logger.info("H.O.D displayed..."+System.lineSeparator());
	}
}
