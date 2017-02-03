package qa.cms;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import qa.BasePage;

public class HomePage extends BasePage {
	
	public Navigation navigation;
	
	@FindBy(how = How.CSS, using="h1.content__header__title")
	private List<WebElement> header;
	
	
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		super(driver);
		navigation = new Navigation(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	@Override
	public Boolean pageIsValid() {
		//return adContainers.size() > 0;
		return true;
	}
	
	
		
	
	
	

}
