package qa.bizjournals.video;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import qa.BasePage;

public class ToutEnabledPage extends BasePage {
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public ToutEnabledPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	public Boolean pageIsValid() {
		List<WebElement> toutScripts = driver.findElements(By.id("tout-js-sdk"));
		return toutScripts.size() >= 1;
	}
	
	public Boolean isSmartArticlePresent() {
		List<WebElement> smartArticleContainers = driver.findElements(By.cssSelector("div.tout-top-article"));
		
		return smartArticleContainers.size() > 0;
	}
	
	public Boolean isToutEmbeddedVideoPresent() {
		List<WebElement> toutEmbedContainer = driver.findElements(By.cssSelector("div.tout-player-wrapper"));
		
		return toutEmbedContainer.size() > 0;
	}
	
	
}