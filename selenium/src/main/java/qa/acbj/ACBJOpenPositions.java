/**
 * 
 */
package qa.acbj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class ACBJOpenPositions {

	@FindBy(how = How.ID, using = "formFilterEmployer")
	private WebElement formFilterEmployer;
	
	@FindBy(how = How.ID, using = "formFilterLocation")
	private WebElement formFilterLocation;

	/**
	 * @return the formFilterEmployer
	 */
	public WebElement getFormFilterEmployer() {
		return formFilterEmployer;
	}

	/**
	 * @return the formFilterLocation
	 */
	public WebElement getFormFilterLocation() {
		return formFilterLocation;
	}
}
