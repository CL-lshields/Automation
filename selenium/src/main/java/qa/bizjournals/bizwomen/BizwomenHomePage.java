package qa.bizjournals.bizwomen;

/*
 * BizwomenHomePage.java
 * 
 * Component ID: 5621
 */

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class BizwomenHomePage {

	@FindBy(how = How.LINK_TEXT, using="SIGN IN")
	private WebElement signInLink;

	/**
	 * @return the signInLink
	 */
	public WebElement getSignInLink() {
		return signInLink;
	}
}
