/**
 * 
 */
package qa.nascarillustrated;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author	Allison Hagge <ahagge@bizjournals.com>
 * @author	Bob Eksten <reksten@bizjournals.com>
 * @version	1.0
 */
public class NascarIllustratedTopNav {

	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Home")
	private WebElement topNavHomeLink;
	
	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Storefront")
	private WebElement topNavStorefrontLink;
	
	
	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Contact")
	private WebElement topNavContactLink;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactModal")
	private WebElement topNavContactModal;
	
	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Advertise")
	private WebElement topNavAdvertiseLink;
	
	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Your Cart")
	private WebElement topNavYourCartLink;

	/**
	 * @return the topNavHomeLink
	 */
	public WebElement getTopNavHomeLink() {
		return topNavHomeLink;
	}

	/**
	 * @return the topNavStorefrontLink
	 */
	public WebElement getTopNavStorefrontLink() {
		return topNavStorefrontLink;
	}

	/**
	 * @return the topNavContactLink
	 */
	public WebElement getTopNavContactLink() {
		return topNavContactLink;
	}
	
	/**
	 * 
	 * @return WebElement topNavContactModal
	 */
	public WebElement getTopNavContactModal() {
		return topNavContactModal;
	}

	/**
	 * @return the topNavAdvertiseLink
	 */
	public WebElement getTopNavAdvertiseLink() {
		return topNavAdvertiseLink;
	}

	/**
	 * @return the topNavYourCartLink
	 */
	public WebElement getTopNavYourCartLink() {
		return topNavYourCartLink;
	}
} /* NascarIllustratedTopNav */
