/**
 * 
 */
package qa.commerce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class BizjournalsArchiveView {

	@FindBy(how = How.LINK_TEXT, using = "See More Weekly Editions")                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	private WebElement seeMoreWeeklyEditionsLink;
	
	/**
	 * @return the seeMoreWeeklyEditionsLink
	 */
	public WebElement getSeeMoreWeeklyEditionsLink() {
		return seeMoreWeeklyEditionsLink;
	}
}
