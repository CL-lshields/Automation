package qa.bizjournals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import qa.BasePage;


public class Footer extends BasePage {
	
	@FindBy(how = How.CSS, using="footer.footer")
	private WebElement footer;
	
	@FindBy(how = How.CSS, using="div.footer__header")
	private WebElement footerHeader;
	
	@FindBy(how = How.CSS, using="div.footer__body")
	private WebElement footerBody;
	
	@FindBy(how = How.CSS, using="div.footer__footer")
	private WebElement footerFooter;

	/**
	 * SOCIAL LINKS
	 */
	@FindBy(how = How.CSS, using="footer a.btn--social.btn--twitter")
	private WebElement twitterLink;
	
	@FindBy(how = How.CSS, using="footer a.btn--social.btn--linkedin")
	private WebElement linkedInLink;
	
	@FindBy(how = How.CSS, using="footer a.btn--social.btn--facebook")
	private WebElement facebookLink;
	
	@FindBy(how = How.CSS, using="footer a.btn--social.btn--googleplus")
	private WebElement googlePlusLink;
	
	/**
	 * Default constructor
	 * 
	 * @param driver
	 */
	public Footer(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Validate the required elements that define the page
	 * 
	 * @return true if the page is valid, false otherwise
	 */
	public Boolean pageIsValid() {
		return true;
	}
	
	public void scrollIntoView() {
		
	}
	
	public void clickTwitterLink() {
		interruptTool.scrollIntoView(footer);
		twitterLink.click();
	}
	
	public void clickLinkedInLink() {
		interruptTool.scrollIntoView(footer);
		linkedInLink.click();
	}
	
	public void clickFacebookLink() {
		interruptTool.scrollIntoView(footer);
		facebookLink.click();
	}
	
	public void clickGooglePlusLink() {
		interruptTool.scrollIntoView(footer);
		googlePlusLink.click();
	}
	
	public void clickLinkByName(String linkText) {
		WebElement link = footer.findElement(By.linkText(linkText));
		link.click();
	}

	
	
	
	
	
	
		
	
	
	

}
