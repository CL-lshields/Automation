/**
 * 
 */
package qa.bizjournals.forms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class BizjournalsSignInForm {

	@FindBy(how = How.ID, using="submit")
	private WebElement signInButton;
	
	@FindBy(how = How.ID, using="emailAddress")
	private WebElement emailAddressInput;
	
	@FindBy(how = How.ID, using="password")
	private WebElement passwordInput;

	/**
	 * 
	 * @return the signInButton
	 */
	public WebElement getSignInButton() {
		return signInButton;
	}
	
	/**
	 * @return the emailAddressInput
	 */
	public WebElement getEmailAddressInput() {
		return emailAddressInput;
	}

	/**
	 * @return the passwordInput
	 */
	public WebElement getPasswordInput() {
		return passwordInput;
	}
}
