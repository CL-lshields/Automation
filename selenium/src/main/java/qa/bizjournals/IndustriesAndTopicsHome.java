package qa.bizjournals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import qa.BasePage;

public class IndustriesAndTopicsHome extends BasePage {
	
	@FindBy(how = How.CSS, using="h1.site-section-header__title--page-title")
	private WebElement pageTitle;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public IndustriesAndTopicsHome(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	public Boolean pageIsValid() {
		return driver.getCurrentUrl().contains("industry-news");
	}
	
	public String getPageTitle() {
		return pageTitle.getText();
	}
	

}
