package qa.bizjournals;

/*
 * PotmEmail.java
 * 
 * Component ID: 1951 - POTM Display Individual
 * See: Bizjournals_Component_Plugin_PotmDisplaySubmission
 */

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author reksten@bizjournals.com
 *
 */
public class PotmEmail {

	@FindBy(how = How.CLASS_NAME, using = "icon-email")
	private WebElement emailLink;
	
	@FindBy(how = How.ID, using = "copyMe")
	private WebElement copyMe;

	@FindBy(how = How.ID, using = "emailTo")
	private WebElement emailTo;
	
	@FindBy(how = How.ID, using = "emailFrom")
	private WebElement emailFrom;	

	@FindBy(how = How.ID, using = "emailNote")
	private WebElement emailNote;
	
	/**
	 * @return the emailLink
	 */
	public WebElement getEmailLink() {
		return emailLink;
	}

	/**
	 * @return the emailTo
	 */
	public WebElement getEmailTo() {
		return emailTo;
	}

	/**
	 * @return the emailLink
	 */
	public WebElement getCopyMe() {
		return copyMe;
	}
	
	/**
	 * @return the emailFrom
	 */
	public WebElement getEmailFrom() {
		return emailFrom;
	}

	/**
	 * @return the emailNote
	 */
	public WebElement getEmailNote() {
		return emailNote;
	}

}
