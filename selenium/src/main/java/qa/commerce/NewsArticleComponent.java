package qa.commerce;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.utility.InterruptTool;

/**
 * Created by root on 4/13/16.
 */
public class NewsArticleComponent extends qa.commerce.BasePage {
	qa.utility.WaitTool wait;
	InterruptTool interrupt;

	public NewsArticleComponent(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new qa.utility.WaitTool(driver);
		interrupt = new InterruptTool(driver);
	}

	@Override
	public boolean atPage() {
		return pageTitleHeader().isDisplayed();
	}

	// --------------------------Elements-------------------------------------//

	private WebElement pageTitleHeader() {
		return driver.findElement(By.tagName("title"));
	}

//	@SuppressWarnings("unused")
//	private WebElement articleMeterPaywall() {
//		return driver.findElement(By.id("paywallMeter"));
//	}

	private WebElement subscribeNowButton() {
		
		return driver.findElement(By.className("paywall--premium")).findElement(By.className("module--padded")).findElement(By.tagName("a"));
//		for (int i = 0; i < driver.findElement(By.className("paywall--premium")).findElements(By.tagName("a"))
//				.size(); i++) {
//			if (driver.findElement(By.className("paywall--premium")).findElements(By.tagName("a")).get(i).getText()
//					.equals("Subscribe Now"));
//			return driver.findElement(By.className("paywall--premium")).findElements(By.tagName("a")).get(i);
//		}
//		return null;
	}

	@SuppressWarnings("unused")
	private WebElement articleFullPaywall() {
		return driver.findElement(By.className("promo--wide__headline"));
	}

	private WebElement articleMeterPaywallCount() {
		return driver.findElement(By.id("paywallMeter")).findElement(By.className("prompt__headline"))
				.findElement(By.className("prompt__headline__counter"));
	}

	// --------------------------Helpers-------------------------------------//

	public boolean isArticleMeterPaywall() {
		return wait.waitForPresenceOf(By.id("paywallMeter"));
	}

	public boolean isArticleFullPaywall() {
		wait.waitForPresenceOf(By.className("promo--wide__headline"));
		return driver.findElement(By.className("promo--wide__headline")).getAttribute("innerHTML").contains("Subscribe to get the full story.");
	}

	public void subscribeToBizjournals() {
		interrupt.scrollIntoView(driver.findElement(By.className("content--embargoed__preview")));
		driver.get(subscribeNowButton().getAttribute("href"));
		Assert.assertTrue("could not navigate to magento checkout!", pageTitleHeader().getAttribute("textContent").equals("Checkout"));
		qa.SeleniumTest.logger.info("Subscribing to Bizjournals..." + System.lineSeparator());
	}

	public String articleCount() {
		return articleMeterPaywallCount().getText();
	}

}