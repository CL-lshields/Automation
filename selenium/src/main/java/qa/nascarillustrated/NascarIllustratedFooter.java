/**
 * 
 */
package qa.nascarillustrated;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author	Allison Hagge <ahagge@bizjournals.com>
 * @version	1.0
 */
public class NascarIllustratedFooter {

	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Terms & Conditions")
	private WebElement termsAndConditionsLink;
	
	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Privacy Policy")
	private WebElement privacyPolicyLink;
	
	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="User Agreement")
	private WebElement userAgreementLink;

	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Your California Privacy Rights")
	private WebElement yourCaliforniaPrivacyRightsLink;

	/**
	 * @return the termsAndConditionsLink
	 */
	public WebElement getTermsAndConditionsLink() {
		return termsAndConditionsLink;
	}

	/**
	 * @return the privacyPolicyLink
	 */
	public WebElement getPrivacyPolicyLink() {
		return privacyPolicyLink;
	}

	/**
	 * @return the userAgreementLink
	 */
	public WebElement getUserAgreementLink() {
		return userAgreementLink;
	}

	/**
	 * @return the yourCaliforniaPrivacyRightsLink
	 */
	public WebElement getYourCaliforniaPrivacyRightsLink() {
		return yourCaliforniaPrivacyRightsLink;
	}
} /* NascarIllustratedFooter */
