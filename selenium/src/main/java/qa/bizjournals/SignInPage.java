package qa.bizjournals;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import qa.BasePage;
import qa.constants.SeleniumConstants;
import qa.utility.InterruptTool;

public class SignInPage extends BasePage {
	InterruptTool interrupt;
	private static final String pageTitleText = "Sign in to your account";
	
	@FindBy(how = How.CSS, using="h1.landing__title")
	private WebElement pageTitle;
	
	@FindBy(how = How.LINK_TEXT, using="Create a FREE account.")
	private WebElement createFreeAccountLink;
	
	@FindBy(how = How.CSS, using="form#loginform input#emailAddress")
	private WebElement emailAddressInput;
	
	@FindBy(how = How.CSS, using="form#loginform input#password")
	private WebElement passwordInput;
	
	@FindBy(how = How.CSS, using="form#loginform button#submit")
	private WebElement signInButton;
	
	//El site profile page elements
	@FindBy(how = How.ID, using="firstname")
	private WebElement firstNameField;	
	
	@FindBy(how = How.ID, using="lastname")
	private WebElement lastNameField;		
	
	private List<WebElement> industryField(){
		return driver.findElement(By.id("industry")).findElements(By.tagName("option"));
	}
		
	private List<WebElement> jobTitleField(){
		return driver.findElement(By.id("industry")).findElements(By.tagName("option"));
	}
	
	private List<WebElement> jobFunctionField(){
		return driver.findElement(By.id("industry")).findElements(By.tagName("option"));
	}		
		
	@FindBy(how = How.ID, using="zip")
	private WebElement zipfield;
		
		
	@FindBy(how = How.ID, using="password")
	private WebElement passwordField;
		
		
	@FindBy(how = How.ID, using="submit")
	private WebElement submitField;
		
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public SignInPage(WebDriver driver) {
		super(driver);
		interrupt = new InterruptTool(driver);
		waitTool.waitForVisibilityOf(pageTitle, SeleniumConstants.MAX_TIMEOUT);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	public Boolean pageIsValid() {
		return pageTitle.getText().equals(pageTitleText);
	}
	
	public Boolean hasValidationError() {
		List<WebElement> validationErrors = driver.findElements(By.cssSelector("div.notification-detail--error"));
		
		return validationErrors.size() > 0;
	}
	
	public CreateAccountPage clickToCreateAccount() {
		createFreeAccountLink.click();
		
		return new CreateAccountPage(driver);
	}
	
	public void signIn(String email, String password) {
		emailAddressInput.sendKeys(email);
		passwordInput.sendKeys(password);
		
		submitForm();
	}
	
	public void submitForm() {
		try{
			signInButton.click();
		}catch(WebDriverException ex){
			interrupt.jsClick(signInButton);
		}
	}
	
	public void createProfile(HashMap<String,String> data){
		firstNameField.sendKeys(data.get("username"));
		lastNameField.sendKeys(data.get("market"));
		industryField().get(1).click();
		jobTitleField().get(1).click();
		jobFunctionField().get(1).click();
		zipfield.sendKeys(data.get("zip"));
		passwordField.sendKeys(data.get("password"));
		submitField.click();
	}
	
}