/**
 * 
 */
package qa.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.constants.SeleniumConstants;

/**
 * @author Bob Eksten <reksten@bizjournals.com>
 *
 */
public class JSExpectedConditions {

	/**
	 * 
	 * Simple method to wait for JavaScript code to be loaded in a page before
	 * continuing with a test.
	 * 
	 * @param driver
	 * @return boolean
	 */
	public static boolean waitForJs(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, SeleniumConstants.MAX_TIMEOUT);

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		return wait.until(jsLoad);
	}
}
