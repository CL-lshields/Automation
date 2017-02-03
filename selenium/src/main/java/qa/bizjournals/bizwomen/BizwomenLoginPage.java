package qa.bizjournals.bizwomen;

/**
 * 
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BizwomenLoginPage
{
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'emailAddress')]")
	private WebElement loginPageEmailAddressInput;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'password')]")
	private WebElement loginPagePasswordInput;

	@FindBy(how = How.XPATH, using = "//button[contains(@value,'submit')]")
	private WebElement loginPageSignInButton;
	
	/**
	 * @return the loginPageEmailAddressInput
	 */
	public WebElement getLoginPageEmailAddressInput() {
		return loginPageEmailAddressInput;
	}

	/**
	 * @return the loginPagePasswordInput
	 */
	public WebElement getLoginPagePasswordInput() {
		return loginPagePasswordInput;
	}

	/**
	 * @return the loginPageSignInButton
	 */
	public WebElement getLoginPageSignInButton() {
		return loginPageSignInButton;
	}	
}
