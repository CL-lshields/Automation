/**
 * 
 */
package qa.upstart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class UpstartGlobalNav {

	@FindBy(how = How.LINK_TEXT, using="Home")
	private WebElement globalNavHomeLink;
	
	@FindBy(how = How.XPATH, using="News")
	private WebElement globalNavNewsLink;
	
	@FindBy(how = How.LINK_TEXT, using="People")
	private WebElement globalNavPeopleLink;
	
	@FindBy(how = How.LINK_TEXT, using="Events")
	private WebElement globalNavEventsLink;
	
	@FindBy(how = How.LINK_TEXT, using="Exclusives")
	private WebElement globalNavExclusivesLink;
	
	@FindBy(how = How.LINK_TEXT, using="How-To")
	private WebElement globalNavHowToLink;
	
	@FindBy(how = How.LINK_TEXT, using="Buy")
	private WebElement globalNavBuyLink;
	
	@FindBy(how = How.LINK_TEXT, using="Jobs")
	private WebElement globalNavJobsLink;
	
	@FindBy(how = How.LINK_TEXT, using="Contact Us")
	private WebElement globalNavContactUsLink;

	/**
	 * @return the globalNavHomeLink
	 */
	public WebElement getGlobalNavHomeLink() {
		return globalNavHomeLink;
	}

	/**
	 * @return the globalNavNewsLink
	 */
	public WebElement getGlobalNavNewsLink() {
		return globalNavNewsLink;
	}

	/**
	 * @return the globalNavPeopleLink
	 */
	public WebElement getGlobalNavPeopleLink() {
		return globalNavPeopleLink;
	}

	/**
	 * @return the globalNavEventsLink
	 */
	public WebElement getGlobalNavEventsLink() {
		return globalNavEventsLink;
	}

	/**
	 * @return the globalNavExclusivesLink
	 */
	public WebElement getGlobalNavExclusivesLink() {
		return globalNavExclusivesLink;
	}

	/**
	 * @return the globalNavHowToLink
	 */
	public WebElement getGlobalNavHowToLink() {
		return globalNavHowToLink;
	}

	/**
	 * @return the globalNavBuyLink
	 */
	public WebElement getGlobalNavBuyLink() {
		return globalNavBuyLink;
	}

	/**
	 * @return the globalNavJobsLink
	 */
	public WebElement getGlobalNavJobsLink() {
		return globalNavJobsLink;
	}

	/**
	 * @return the globalNavContactUsLink
	 */
	public WebElement getGlobalNavContactUsLink() {
		return globalNavContactUsLink;
	}	
}