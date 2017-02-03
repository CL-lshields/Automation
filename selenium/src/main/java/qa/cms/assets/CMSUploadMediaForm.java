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
public class CMSUploadMediaForm {

	@FindBy(how = How.ID, using="uploader-files")
	private WebElement uploadForm;

	//*[@id="uploader-files"]	
	
	/**
	 * @return the addFilesButton
	 */
	public WebElement getUploadForm() {
		return uploadForm;
	}	
}
