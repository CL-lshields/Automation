package qa.bizjournals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import qa.BasePage;
import qa.SeleniumTest;

import java.util.List;

public class FeaturedStory extends BasePage {
	
	@FindBy(how = How.CSS, using="a.item--featured img")
	protected WebElement heroImage;
	
	@FindBy(how = How.CSS, using="a.item--featured")
	protected List<WebElement> featureLinks;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public FeaturedStory(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	public Boolean pageIsValid() {
		
		
		return heroImage.isDisplayed();
	}
	
	/**
	 * Navigate to the featured story
	 */
	public void clickFeatureLink() {
		SeleniumTest.logger.info("Navigating to " + featureLinks.get(0).getAttribute("href"));
		featureLinks.get(0).click();
	}
	

}
