package qa.bizjournals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import qa.BasePage;
import qa.constants.SeleniumConstants;

public class CreateAccountPage extends BasePage {
	
	private static final String pageTitleText = "Register for a New Account - The Business Journals";
	
	@FindBy(how = How.CSS, using="h1.landing__title")
	private WebElement pageTitle;
	
	// TODO: we need a better way to locate this link
	@FindBy(how = How.LINK_TEXT, using="Sign In")
	private WebElement signInLink;
	
	@FindBy(how = How.CSS, using="form#registrationform input#emailAddress")
	private WebElement emailAddressInput;
	
	@FindBy(how = How.CSS, using="form#registrationform input#newPassword")
	private WebElement passwordInput;
	
	@FindBy(how = How.CSS, using="form#registrationform input#firstName")
	private WebElement firstNameInput;
	
	@FindBy(how = How.CSS, using="form#registrationform input#lastName")
	private WebElement lastNameInput;
	
	@FindBy(how = How.CSS, using="form#registrationform select#jobTitle")
	private WebElement jobTitleSelect;
	
	@FindBy(how = How.CSS, using="form#registrationform select#industry")
	private WebElement industrySelect;
	
	@FindBy(how = How.CSS, using="form#registrationform input#zip")
	private WebElement zipCodeInput;
	
	@FindBy(how = How.CSS, using="form#registrationform button")
	private WebElement createAccountButton;
		
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public CreateAccountPage(WebDriver driver) {
		super(driver);
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
	
	public SignInPage clickToSignIn() {
		signInLink.click();
		return new SignInPage(driver);
	}
	
	public void createFreeAccount(String email, String password, String firstName, String lastName, String jobTitle, String industry, String zipCode) {
		enterEmail(email);
		enterPassword(password);
		enterFirstName(firstName);
		enterLastName(lastName);
		selectJobTitle(jobTitle);
		selectIndustry(industry);
		enterZipCode(zipCode);
		submitForm();
	}
	
	public void enterEmail(String email) {
		emailAddressInput.sendKeys(email.trim());
	}
	
	public void enterPassword(String password) {
		passwordInput.sendKeys(password.trim());
	}
	
	public void enterFirstName(String firstName) {
		firstNameInput.sendKeys(firstName.trim());
	}
	
	public void enterLastName(String lastName) {
		lastNameInput.sendKeys(lastName.trim());
	}
	
	public void selectJobTitle(String jobTitle) {
		Select select = new Select(jobTitleSelect);
		select.selectByVisibleText(jobTitle);
	}
	
	public void selectIndustry(String industry) {
		Select select = new Select(industrySelect);
		select.selectByVisibleText(industry);
	}
	
	public void enterZipCode(String zipCode) {
		zipCodeInput.sendKeys(zipCode.trim());
	}
	
	public void submitForm() {
		createAccountButton.click();
	}
	
	
}