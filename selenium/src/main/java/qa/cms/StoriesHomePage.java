package qa.cms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import qa.BasePage;

public class StoriesHomePage extends BasePage {
	
	@FindBy(how = How.CSS, using="h1.content__header__title")
	private WebElement pageHeader;
	
	@FindBy(how = How.LINK_TEXT, using="Add Story")
	private WebElement addStoryButton;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public StoriesHomePage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	@Override
	public Boolean pageIsValid() {
		return pageHeader.getText().equals("Stories");
	}
	
	public EditStoryForm addStory() {
		addStoryButton.click();
		
		return new EditStoryForm(driver);
	}
	
	
	
	
		
	
	
	

}
