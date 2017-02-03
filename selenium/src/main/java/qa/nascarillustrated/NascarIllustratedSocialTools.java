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
 * @version 1.0
 */
public class NascarIllustratedSocialTools {

	/**
	 * 
	 */
	@FindBy(how = How.XPATH, using="//ul[contains(@class, \"social-links\")]/li/a[contains(@class, \"icon-twitter\")]")
	private WebElement socialToolsTwitterLink;
	
	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="//ul[contains(@class, \"social-links\")]/li/a[contains(@class, \"icon-facebook\")]")
	private WebElement socialToolsFacebookLink;

	/**
	 * 
	 * @return WebElement socialToolsTwitterLink
	 */
	public WebElement getSocialToolsTwitterLink() {
		return socialToolsTwitterLink;
	}

	/**
	 * 
	 * @return WebElement socialToolsFacebookLink
	 */
	public WebElement getSocialToolsFacebookLink() {
		return socialToolsFacebookLink;
	}
} /* NascarIllustratedSocialTools */
