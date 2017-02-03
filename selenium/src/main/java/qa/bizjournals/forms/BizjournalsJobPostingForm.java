package qa.bizjournals.forms;

/*
 * BizjournalsJobPostingForm.java
 */

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class BizjournalsJobPostingForm {

	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "full_name")
	private WebElement fullName;

	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "phone_number")
	private WebElement phoneNumber;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "email_address")
	private WebElement emailAddress;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "job_title")
	private WebElement jobTitle;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "company_name")
	private WebElement companyName;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "company_url")
	private WebElement companyUrl;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "job_zip_code")
	private WebElement jobZipCode;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "job_market")
	private WebElement jobMarket;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "job_cat1")
	private WebElement jobCat1;

	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "job_cat2")
	private WebElement jobCat2;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "job_cat3")
	private WebElement jobCat3;
	
	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "job_description")
	private WebElement jobDescription;
	
	/**
	 * 
	 */
	@FindBy(how = How.NAME, using = "application_url")
	private WebElement applicationUrl;

	/**
	 * 
	 */
	@FindBy(how = How.ID, using = "jobs-post-form-submit")
	private WebElement submitButton;
	
	/**
	 * @return the fullName
	 */
	public WebElement getFullName() {
		return fullName;
	}

	/**
	 * @return the phoneNumber
	 */
	public WebElement getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the emailAddress
	 */
	public WebElement getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @return the jobTitle
	 */
	public WebElement getJobTitle() {
		return jobTitle;
	}

	/**
	 * @return the companyName
	 */
	public WebElement getCompanyName() {
		return companyName;
	}

	/**
	 * @return the companyUrl
	 */
	public WebElement getCompanyUrl() {
		return companyUrl;
	}

	/**
	 * @return the jobZipCode
	 */
	public WebElement getJobZipCode() {
		return jobZipCode;
	}

	/**
	 * @return the jobMarket
	 */
	public WebElement getJobMarket() {
		return jobMarket;
	}

	/**
	 * @return the jobCat1
	 */
	public WebElement getJobCat1() {
		return jobCat1;
	}

	/**
	 * @return the jobCat2
	 */
	public WebElement getJobCat2() {
		return jobCat2;
	}
	
	/**
	 * @return the jobCat3
	 */
	public WebElement getJobCat3() {
		return jobCat3;
	}

	/**
	 * @return the jobCat3
	 */
	public WebElement getJobDescription() {
		return jobDescription;
	}
	
	/**
	 * @return the applicationUrl
	 */
	public WebElement getApplicationUrl() {
		return applicationUrl;
	}

	/**
	 * @return the applicationUrl
	 */
	public WebElement getSubmitButton() {
		return submitButton;
	}
}
