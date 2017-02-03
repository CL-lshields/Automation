package qa.events.modules;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.junit.Assert;

import qa.ComponentFactory;
import qa.utility.WaitTool;

public class BizJournalsEvents {

	private WebDriver driver;
	private ComponentFactory components;
	@SuppressWarnings("unused")
	private WaitTool waitTool;

	public BizJournalsEvents(WebDriver driver) {
		this.driver = driver;
		components = new ComponentFactory(driver);
		waitTool = new WaitTool(driver);
	}

	public ComponentFactory getComponents() {
		return components;
	}

	/**
	 * Compare prices compares the current price to the discounted price of an
	 * item after a promotion is used to ensure that the price is correct. A
	 * rate is passed to the function which should normally be obtained through
	 * the data sheet using the ExcelDriver.
	 * 
	 * @param rate
	 *            - percentage of the discount applied
	 * @return - boolean - is the discounted price correct?
	 */
	private boolean comparePrices(double rate) {
		String principal = driver.findElement(By.className("dataTab")).findElements(By.tagName("tr")).get(1)
				.findElements(By.tagName("td")).get(2).getText();
		principal = principal.replace("$", "");
		principal = principal.replace(".00", "");
		String current = driver.findElement(By.className("dataTab")).findElements(By.tagName("tr")).get(3)
				.findElements(By.tagName("td")).get(2).getText();
		current = current.replace("$", "");
		current = current.replace(".00", "");
		int principalInt = Integer.parseInt(principal);
		int currentInt = Integer.parseInt(current);
		double percent = rate * .01;
		if ((principalInt * percent) == currentInt) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that the price of an item in checkout is correct. Accounts for discounts 
	 * by event, or global discounts. The function does not currently account for both.
	 * 
	 * @param data - ExcelDriver datamap
	 */
	public void verifyCheckout(HashMap<String, String> data) {
		// Verify any discounts before checkout
		if (driver.findElement(By.className("dataTab")).findElements(By.tagName("tr")).get(2)
				.findElements(By.tagName("td")).get(1).getText().equalsIgnoreCase("discount")) {

			if (driver.findElement(By.className("dataTab")).findElements(By.tagName("tr")).get(2)
					.findElements(By.tagName("td")).get(0).findElement(By.tagName("strong")).getText()
					.equalsIgnoreCase("QATEST")) {
				Assert.assertTrue("Promotion price missing!", driver.findElement(By.className("dataTab"))
						.findElements(By.tagName("tr")).get(3).findElements(By.tagName("td")).get(2).isDisplayed());
			} else {
				// Assert that promotion is same as in data
				Assert.assertTrue("Incorrect promotion",
						driver.findElement(By.className("dataTab")).findElements(By.tagName("tr")).get(2)
								.findElements(By.tagName("td")).get(0).findElement(By.tagName("strong")).getText()
								.equalsIgnoreCase(data.get("code")));

				Assert.assertTrue("Incorrect promotion rate!", comparePrices(Double.parseDouble(data.get("promo"))));
			}
		}
		driver.findElement(By.linkText("Check Out")).click();
	}

	//Click edit order link
	public void editOrder() {
		driver.findElement(By.className("edit_order")).click();
	}

	//click remove item from cart link
	public void removeItemFromCart() {
		driver.findElement(By.linkText("Remove Item")).click();
	}
}
