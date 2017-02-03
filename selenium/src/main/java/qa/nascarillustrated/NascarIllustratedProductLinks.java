/**
 * 
 */
package qa.nascarillustrated;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author	Allison Hagge <ahagge@bizjournals.com>
 * @author	Bob Eksten <reksten@bizjournals.com>
 * @version	1.0
 */
public class NascarIllustratedProductLinks {

	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="NASCAR Illustrated Single Issues")
	private WebElement productsNascarIllustratedSingleIssuesLink;
	
	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="NASCAR Illustrated Cover Reproductions")
	private WebElement productsNascarIllustratedCoverReprosLink;

	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="NASCAR Scene Single Issues")
	private WebElement productsNascarIllustratedSceneSingleIssuesLink;

	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Books")
	private WebElement productsNascarIllustratedBooksLink;
	
	/**
	 * 
	 */
	@FindBy(how = How.LINK_TEXT, using="Games")
	private WebElement productsNascarIllustratedGamesLink;
	
	/**
	 * 
	 * @return WebElement productsNascarIllustratedSingleIssuesLink
	 */
	public WebElement getProductsNascarIllustratedSingleIssuesLink() {
		return productsNascarIllustratedSingleIssuesLink;
	}
	
	/**
	 * 
	 * @return WebElement productsNascarIllustratedCoverReprosLink
	 */
	public WebElement getProductsNascarIllustratedCoverReprosLink() {
		return productsNascarIllustratedCoverReprosLink;
	}
	
	/**
	 * @return the productsNascarIllustratedSceneSingleIssuesLink
	 */
	public WebElement getProductsNascarIllustratedSceneSingleIssuesLink() {
		return productsNascarIllustratedSceneSingleIssuesLink;
	}

	/**
	 * @return the productsNascarIllustratedBooksLink
	 */
	public WebElement getProductsNascarIllustratedBooksLink() {
		return productsNascarIllustratedBooksLink;
	}

	/**
	 * @return the productsNascarIllustratedGamesLink
	 */
	public WebElement getProductsNascarIllustratedGamesLink() {
		return productsNascarIllustratedGamesLink;
	}
} /* NascarIllustratedProductLinks */
