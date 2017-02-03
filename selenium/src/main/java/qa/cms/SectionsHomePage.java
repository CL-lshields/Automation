package qa.cms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import qa.BasePage;

public class SectionsHomePage extends BasePage {
	
	@FindBy(how = How.CSS, using="h1.content__header__title")
	private WebElement pageHeader;
	
	@FindBy(how = How.LINK_TEXT, using="Add Section")
	private WebElement addSectionButton;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public SectionsHomePage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	public Boolean pageIsValid() {
		return pageHeader.getText().equals("Sections");
	}
	
	public EditSectionForm addSection() {
		addSectionButton.click();
		
		return new EditSectionForm(driver);
	}
	
	
	
	
		
	
	
	

}
