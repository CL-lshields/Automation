/*
 * Models the Responsive Header for Bizjournals National Homepage
 * @author	Allison Hagge <ahagge@bizjournals.com>
 */

package qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.constants.SeleniumConstants;
import qa.utility.InterruptTool;
import qa.utility.WaitTool;

public abstract class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected WaitTool waitTool;
	protected InterruptTool interruptTool;
	
	@FindBy(how = How.CSS, using="meta[name=\"market:json\"]")
	private WebElement marketJsonMetaData;
	
	public BasePage(WebDriver driver) {
		
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 15);
		
		// initialize page elements
		PageFactory.initElements(finder, this);	
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);
		this.interruptTool = new InterruptTool(driver);
		this.waitTool = new WaitTool(driver);
		
		/*// validate the page using the extended classes implementation of pageIsValid
		if(!pageIsValid()) {
			throw new IllegalStateException("Page failed validation!");
		}*/
		
	}
	
	protected abstract Boolean pageIsValid();
	
	public Boolean pageIs404() {
		return driver.getTitle().contains("The page you requested was not found");
	}
	
	public void hoverOverElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();		
	}
	
	public String getMarketName() {
		System.out.println("Getting market name");
		System.out.println(marketJsonMetaData.toString());
		return marketJsonMetaData.getAttribute("market_name");
	}
	
	public String getMarketCode() {
		return driver.findElement(By.cssSelector("meta[name=\"market\\:json\"]")).getAttribute("market_code");
	}
	
	

}


