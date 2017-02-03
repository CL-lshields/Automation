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

/*
 * http://www.bizjournals.com/about-us/contact/
 * consists of four accordian forms:
 *
 * Subscription Help
 * Advertising
 * Business Directory Assistance
 * Contact Us
 */

public class BizjournalsContactUsForm {
	
	/* Subscription Help form */
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_Name")
	private WebElement subscriberContactName;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_address1")
	private WebElement subscriberContactAddress1;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_address2")
	private WebElement subscriberContactAddress2;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_city")
	private WebElement subscriberContactCity;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_stateCode")
	private WebElement subscriberContactStateCode;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_zipCode")
	private WebElement subscriberContactZipCode;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_Phone")
	private WebElement subscriberContactPhone;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_emailAddress")
	private WebElement subscriberContactEmailAddress;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_emailAddressConfirm")
	private WebElement subscriberContactEmailAddressConfirm;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_Businessjournal")
	private WebElement subscriberContactJournal;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_accountNumber")
	private WebElement subscriberContactAccountNumber;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_problemtype")
	private WebElement subscriberContactProblemType;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactSubs_txtareaComments")
	private WebElement subscriberContactComments;
	
	/* Business Directory Assistance */
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactDirectory_Name")
	private WebElement businessDirectoryName;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactDirectory_Company")
	private WebElement businessDirectoryCompany;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactDirectory_Phone")
	private WebElement businessDirectoryPhone;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactDirectory_emailAddress")
	private WebElement businessDirectoryEmail;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactDirectory_emailAddressConfirm")
	private WebElement businessDirectoryEmailConfirm;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contactDirectory_txtareaComment")
	private WebElement businessDirectoryComment;
	
	/**
	 * 
	 */
	@FindBy(how = How.XPATH, using="//*[@id='contactDirectoryForm']/button")
	private WebElement businessDirectorySubmitButton;
	
	/* Contact Us form */
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using="contact_General")
	private WebElement contactUsGeneral;	
}
