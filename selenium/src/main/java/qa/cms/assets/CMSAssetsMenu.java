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
public class CMSAssetsMenu {

	@FindBy(how = How.CSS, using="#nav-admin-assets > a.dropdown-toggle > b.caret")
	private WebElement assetsLink;

	@FindBy(how = How.XPATH, using="//*[@id='nav-admin-assets']/ul/li[1]/a")
	private WebElement mediaSubMenu;

	@FindBy(how = How.XPATH, using="//*[@id='nav-admin-assets']/ul/li[2]/a")
	private WebElement galleriesSubMenu;

	@FindBy(how = How.XPATH, using="//*[@id='nav-admin-assets']/ul/li[3]/a")
	private WebElement recentUploadsSubMenu;
	
	@FindBy(how = How.XPATH, using="//*[@id='nav-admin-assets']/ul/li[4]/a")
	private WebElement uploadMediaSubMenu;
	
	/**
	 * @return the assetsLink
	 */
	public WebElement getAssetsLink() {
		return assetsLink;
	}

	/**
	 * 
	 * @return the galleriesSubMenu
	 */
	public WebElement getGalleriesSubMenu() {
		return mediaSubMenu;
	}	
	
	/**
	 * 
	 * @return the mediaSubMenu
	 */
	public WebElement getMediaSubMenu() {
		return mediaSubMenu;
	}

	/**
	 * 
	 * @return the recentUploadsSubMenu
	 */
	public WebElement getRecentUploadsSubMenu() {
		return recentUploadsSubMenu;
	}
	
	/**
	 * 
	 * @return the uploadMediaSubMenu
	 */
	public WebElement getUploadMediaSubMenu() {
		return uploadMediaSubMenu;
	}
}
