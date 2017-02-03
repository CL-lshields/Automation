package qa.cms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import qa.BasePage;

public class LoginPage extends BasePage {
	
	@FindBy(how = How.CSS, using="input[name=\"identity\"]")
	private WebElement usernameField;
	
	@FindBy(how = How.CSS, using="input[name=\"credential\"]")
	private WebElement passwordField;
	
	@FindBy(how = How.CSS, using="button.btn-primary")
	private WebElement loginButton;
	
	@FindBy(how = How.CSS, using="div.primary__content div.alert-error")
	private List<WebElement> errors;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	@Override
	public Boolean pageIsValid() {
		return usernameField.isDisplayed();		
	}
	
	/**
	 * Enter username and password for an invalid user.  An empty username and/or password
	 * is acceptable.  We expect control to go back to the Login page when Login form is
	 * submitted.
	 * 
	 * @return CmsLogin page
	 */
	public LoginPage loginInvalidUser(String username, String password) {
		
		loginUser(username, password);	
		
		return new LoginPage(driver);
	}
	
	public HomePage loginValidUser(String username, String password) {
		loginUser(username, password);	
		
		return new HomePage(driver);
	}
	
	public Boolean isErrorPresent() {
		
		return errors.size() > 0;
	}
	
	public String getErrorMessage() {
		return errors.get(0).findElement(By.tagName("h4")).getText();
	}
	
	private HomePage loginUser(String username, String password) {
		// enter username
		usernameField.clear();
		usernameField.sendKeys(username);
		
		// enter password
		passwordField.clear();
		passwordField.sendKeys(password);
		
		// attempt to login
		loginButton.click();
		
		return new HomePage(driver);
	}
	
	
		
	
	
	

}
