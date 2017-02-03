package qa.cms;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.BasePage;
import qa.constants.SeleniumConstants;

public class EditSectionForm extends BasePage {
	
	/**
	 * FORM MESSAGES AND PLACEHOLDERS
	 */
	public String successfulSaveMessage = "Your changes have been saved";
	public String primaryChannelPlaceholderText = "Used for targeting advertisements and sponsorships.";
	public String specialCateogryPlaceholderText = "Type to filter categories…";
	
	/**
	 * FORM INPUTS
	 */
	@FindBy(how = How.NAME, using="name")
	private WebElement name;
	
	@FindBy(how = How.NAME, using="paths[]")
	private WebElement paths;
	
	@FindBy(how = How.ID, using="s2id_autogen5")
	private WebElement primaryChannelSelect;
	
	@FindBy(how = How.ID, using="s2id_autogen6_search")
	private WebElement primaryChannelInput;
	
	@FindBy(how = How.CSS, using="div#s2id_autogen5 a.select2-choice abbr.select2-search-choice-close")
	private WebElement primaryChannelDeleteLink;
	
	@FindBy(how = How.ID, using="select2-chosen-6")
	private WebElement selectedPrimaryChannel;
	
	/**
	 * OPTIONAL ITEMS
	 */
	@FindBy(how = How.ID, using="s2id_autogen7")
	private WebElement specialCategorySelect;
	
	@FindBy(how = How.ID, using="s2id_autogen8_search")
	private WebElement specialCategoryInput;
	
	@FindBy(how = How.CSS, using="div#s2id_autogen7 a.select2-choice abbr.select2-search-choice-close")
	private WebElement specialCategoryDeleteLink;
	
	@FindBy(how = How.ID, using="select2-chosen-8")
	private WebElement selectedSpecialCategory;
	
	
	/**
	 * FORM CONTROLS
	 */
	
	@FindBy(how = How.CSS, using="div.content__header__actions button")
	private WebElement topSaveButton;
	
	@FindBy(how = How.CSS, using="div.primary-actions-footer div.primary-actions button")
	private WebElement bottomSaveButton;
	
	
	/**
	 * ALERTS
	 */
	
	@FindBy(how = How.ID, using="alert-error")
	private WebElement errorAlert;
	
	@FindBy(how = How.CSS, using="div.alert-success h4")
	private WebElement successAlert;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public EditSectionForm(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	public Boolean pageIsValid() {
		return true;
	}
	
	/**
	 * Set the text of the name field
	 * @param text
	 */
	public void setName(String text) {
		name.sendKeys(text);
	}
	
	/**
	 * Set the primary channel custom select
	 * @param channel
	 */
	public void setPrimaryChannel(String channel) {
		primaryChannelSelect.click();
		WebDriverWait wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(primaryChannelInput));
		primaryChannelInput.sendKeys(channel);
		primaryChannelInput.sendKeys(Keys.ENTER);
	}
	
	/**
	 * Get the text of the selected primary channel
	 * @return primary channel text
	 */
	public String getPrimaryChannel() {
		return selectedPrimaryChannel.getText();
	}
	
	/**
	 * Remove the selected primary channel
	 */
	public void removePrimaryChannel() {
		primaryChannelDeleteLink.click();		
	}
	
	/**
	 * Set the primary channel custom select
	 * @param channel
	 */
	public void setSpecialCategory(String category) {
		specialCategorySelect.click();
		WebDriverWait wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(specialCategoryInput));
		specialCategoryInput.sendKeys(category);
		specialCategoryInput.sendKeys(Keys.ENTER);
	}
	
	/**
	 * Get the text of the selected primary channel
	 * @return primary channel text
	 */
	public String getSpecialCategory() {
		return selectedSpecialCategory.getText();
	}
	
	/**
	 * Remove the selected primary channel
	 */
	public void removeSpecialCategory() {
		specialCategoryDeleteLink.click();		
	}
	
	/**
	 * Save the form using the button at the top of the form.
	 */
	public void save() {
		topSaveButton.click();
	}
	
	/**
	 * Get the error message text after form submission.
	 * @return error message text
	 */
	public String getErrorMessage() {
		return errorAlert.getText();
	}
	
	/**
	 * Get the success message text after form submission.
	 * @return success message text
	 */
	public String getSuccessMessage() {
		return successAlert.getText();
	}
	
	
	
}
