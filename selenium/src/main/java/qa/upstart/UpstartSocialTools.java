package qa.upstart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UpstartSocialTools {

	@FindBy(how = How.LINK_TEXT, using="Facebook")
	private WebElement socialToolsFacebookLink;

	/**
	 * @return the socialToolsFacebookLink
	 */
	public WebElement getSocialToolsFacebookLink() {
		return socialToolsFacebookLink;
	}	
}
