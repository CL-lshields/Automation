package qa.commerce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BizjournalsPremiumPaywall {

	@FindBy(how = How.LINK_TEXT, using="Subscribe Now")                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	private WebElement selectPrintAndDigitalButton;
	
	@FindBy(how = How.CSS, using="a[class='btn weekly']")
	private WebElement selectDigitalOnlyButton;

	/**
	 * @return the selectPrintAndDigitalButton
	 */
	public WebElement getSelectPrintAndDigitalButton() {
		return selectPrintAndDigitalButton;
	}

	/**
	 * @return the selectDigitalOnlyButton
	 */
	public WebElement getSelectDigitalOnlyButton() {
		return selectDigitalOnlyButton;
	}
}
