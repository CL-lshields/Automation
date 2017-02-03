/**
 * 
 */
package qa.acbj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class ACBJApplicationForm {

	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "full-name")
	private WebElement fullNameInput;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "email")
	private WebElement emailInput;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "phone")
	private WebElement phoneInput;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "website")
	private WebElement websiteInput;
	
	@FindBy(how = How.ID, using = "linkedin")
	private WebElement linkedinInput;
	
	@FindBy(how = How.ID, using = "event-save-btn")
	private WebElement submitButton;

	/**
	 * @return the fullNameInput
	 */
	public WebElement getFullNameInput() {
		return fullNameInput;
	}

	/**
	 * @return the emailInput
	 */
	public WebElement getEmailInput() {
		return emailInput;
	}

	/**
	 * @return the phoneInput
	 */
	public WebElement getPhoneInput() {
		return phoneInput;
	}

	/**
	 * @return the websiteInput
	 */
	public WebElement getWebsiteInput() {
		return websiteInput;
	}

	/**
	 * @return the linkedinInput
	 */
	public WebElement getLinkedinInput() {
		return linkedinInput;
	}

	/**
	 * @return the submitButton
	 */
	public WebElement getSubmitButton() {
		return submitButton;
	}
}
