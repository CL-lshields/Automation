/**
 * 
 */
package qa.cms.assets;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class CMSAssetsSearchForm {

	@FindBy(how = How.XPATH, using="//input[@name='term'])[3]")
	private WebElement cmsSearchFormInput;

	/**
	 * @return the cmsSearchFormInput
	 */
	public WebElement getCmsSearchFormInput() {
		return cmsSearchFormInput;
	}	
}
